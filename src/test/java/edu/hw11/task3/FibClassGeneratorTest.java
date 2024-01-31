package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FibClassGeneratorTest {

    @Test
    @DisplayName("Тестирование FibClassGenerator#generate")
    public void generate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object object = FibClassGenerator.generate();
        Class<?> clazz = object.getClass();
        Method method = clazz.getMethod("fib", int.class);
        Object result = method.invoke(object, 10);
        Assertions.assertThat(result).isEqualTo(55L);
    }
}
