package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {

    @Test
    @DisplayName("Создание файла")
    void fileCreateTest()  {
        DiskMap diskMap = new DiskMap();
        diskMap.dataMap.put("key1", "value1");
        diskMap.dataMap.put("key2", "value2");

        Path filePath = Paths.get("src/main/java/edu/hw6/Task1/files/file.txt");

        diskMap.saveToFile(filePath);

        assertTrue(Files.exists(filePath));
    }

    @Test
    @DisplayName("Загрузка из файла")
    void uploadTest()  {
        Path filePath = Paths.get("src/main/java/edu/hw6/Task1/files/file.txt");

        DiskMap diskMap = new DiskMap(filePath);

        List<String> data = new ArrayList<>();

        for (var x : diskMap.dataMap.entrySet()) {
            data.add(x.getKey() + ":" + x.getValue());
        }

        List<String> expectedResult = new ArrayList<>(List.of("key1:value1", "key2:value2"));
        assertEquals(expectedResult, data);
    }

}
