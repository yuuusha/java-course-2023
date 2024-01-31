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
        DiskMap diskMap = new DiskMap("src/main/resources/hw6/Task1/file.txt");
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertTrue(Files.exists(Path.of("src/main/resources/hw6/Task1/file.txt")));
    }

    @Test
    @DisplayName("Загрузка из файла")
    void uploadTest()  {
        DiskMap diskMap = new DiskMap("src/main/resources/hw6/Task1/file.txt");
        assertEquals(diskMap.get("key1"), "value1");
        assertEquals(diskMap.get("key2"), "value2");
    }

}
