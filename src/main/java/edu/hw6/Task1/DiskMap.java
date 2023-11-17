package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DiskMap {
    public Map<String, String> dataMap;

    public DiskMap() {
        dataMap = new HashMap<>();
    }

    public DiskMap(Path filePath) {
        this.dataMap = new HashMap<>();
        loadFromFile(filePath);
    }

    private void loadFromFile(Path filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    dataMap.put(parts[0], parts[1]);
                } else {
                    throw new RuntimeException("Неверный формат строки входного файла");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToFile(Path filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
