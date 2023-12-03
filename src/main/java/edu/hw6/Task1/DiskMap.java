package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Path path;

    public DiskMap(String path) {
        this.path = Paths.get(path);
    }

    private Map<String, String> uploadMap() {
        Map<String, String> map = new HashMap<>();

        File file = path.toFile();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                } else {
                    throw new RuntimeException("Ошибка формата файла");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    private void saveMap(Map<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return uploadMap().size();
    }

    @Override
    public boolean isEmpty() {
        return uploadMap().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return uploadMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return uploadMap().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return uploadMap().get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        Map<String, String> map = uploadMap();
        String oldValue = map.put(key, value);
        saveMap(map);

        return oldValue;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> map = uploadMap();
        String oldValue = map.remove(key);
        saveMap(map);

        return oldValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        Map<String, String> map = uploadMap();
        map.putAll(m);
        saveMap(map);
    }

    @Override
    public void clear() {
        saveMap(new HashMap<>());
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return uploadMap().keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return uploadMap().values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return uploadMap().entrySet();
    }
}
