package edu.hw10.Task2.cache;

import edu.hw10.Task2.serializers.LongSerializer;
import edu.hw10.Task2.serializers.Serializer;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public final class CacheProxy {
    private CacheProxy() {
    }

    private static final Map<Class<?>, Serializer<?>> SERIALIZER_MAP = new HashMap<>();

    public static <T> T create(T object, Path path) {
        SERIALIZER_MAP.put(Long.class, new LongSerializer());
        SERIALIZER_MAP.put(long.class, new LongSerializer());
        return createProxy(object, path);
    }

    public static <T> T create(T object) {
        SERIALIZER_MAP.put(Long.class, new LongSerializer());
        SERIALIZER_MAP.put(long.class, new LongSerializer());
        return createProxy(object);
    }

    private static <T> T createProxy(T object, Path path) {
        return (T) Proxy.newProxyInstance(
            object.getClass().getClassLoader(),
            object.getClass().getInterfaces(),
            new CacheInvocationHandler<>(object, path)
        );
    }

    private static <T> T createProxy(T object) {
        return (T) Proxy.newProxyInstance(
            object.getClass().getClassLoader(),
            object.getClass().getInterfaces(),
            new CacheInvocationHandler<>(object)
        );
    }

    private final static class CacheInvocationHandler<T> implements InvocationHandler {
        private final Map<Method, Map<List<Object>, Object>> cachedValues = new HashMap<>();

        private final T cachingObject;

        private final Path path;

        private CacheInvocationHandler(T cachingObject, Path path) {
            this.cachingObject = cachingObject;
            this.path = path;
        }

        private CacheInvocationHandler(T cachingObject) {
            this.cachingObject = cachingObject;
            this.path = null;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                Cache cache = method.getAnnotation(Cache.class);
                if (cache.persist()) {
                    return computePersistCache(method, args);
                }
                return computeCache(method, args);
            }
            return method.invoke(cachingObject, args);
        }

        private Object computeCache(Method method, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
            List<Object> argsList;
            if (args != null) {
                argsList = Arrays.asList(args);
            } else {
                argsList = null;
            }

            if (!cachedValues.containsKey(method)) {
                cachedValues.put(method, new HashMap<>());
            }

            var values = cachedValues.get(method);
            if (!values.containsKey(argsList)) {
                Object result = method.invoke(cachingObject, args);
                values.put(argsList, result);
                return result;
            }

            return values.get(argsList);
        }

        private Object computePersistCache(Method method, Object[] args) {
            Serializer<?> serializer = SERIALIZER_MAP.get(method.getReturnType());

            if (serializer == null) {
                throw new IllegalArgumentException(
                    "Не найдено необходимого сериализатора для типа: " + method.getReturnType());
            }

            if (args == null || args.length == 0) {
                return cacheWithNoArgs(method, args, serializer);
            } else {
                return cacheWithArgs(method, args, serializer);
            }
        }

        private Object cacheWithNoArgs(Method method, Object[] args, Serializer<?> serializer) {
            if (Files.exists(path.resolve(method.getName()))) {
                try {
                    return serializer.deserialize(Files.readString(path.resolve(method.getName())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Object result = method.invoke(cachingObject, args);
                    Files.writeString(path.resolve(method.getName()), serializer.serialize(result));
                    return result;
                } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private Object cacheWithArgs(Method method, Object[] args, Serializer<?> serializer) {
            StringBuilder arguments = new StringBuilder();
            for (Object arg : args) {
                Serializer<?> argumentSerializer = SERIALIZER_MAP.get(arg.getClass());

                if (argumentSerializer == null) {
                    throw new IllegalArgumentException("Класс " + arg.getClass() + "не поддерживаем для сериализации");
                }

                arguments.append(argumentSerializer.serialize(arg)).append('_');
            }
            String serializedArguments = arguments.toString();
            String fileName = getFileName(method);

            Properties properties = new Properties();
            if (Files.exists(path.resolve(fileName))) {
                try {
                    properties.load(Files.newBufferedReader(path.resolve(fileName)));

                    if (properties.containsKey(serializedArguments)) {
                        return serializer.deserialize(properties.getProperty(serializedArguments));
                    }

                    return calculateAndSaveCache(method, args, serializer, properties, serializedArguments);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return calculateAndSaveCache(method, args, serializer, properties, serializedArguments);
            }
        }

        private Object calculateAndSaveCache(
            Method method,
            Object[] args,
            Serializer<?> serializer,
            Properties properties,
            String serializedArguments
        ) {
            try {
                Object result = method.invoke(cachingObject, args);
                properties.put(serializedArguments, serializer.serialize(result));
                properties.store(Files.newBufferedWriter(path.resolve(getFileName(method))), null);
                return result;
            } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        private String getFileName(Method method) {
            return method.getName() + Arrays.stream(method.getParameters())
                .map(param -> "_" + param.getType().getName())
                .collect(Collectors.joining());
        }

    }
}
