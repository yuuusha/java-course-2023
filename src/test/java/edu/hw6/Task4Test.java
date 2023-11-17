package edu.hw6;

import edu.hw6.Task4.OutputStreamManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    @Test
    @DisplayName("Создание файла")
    void createFile() {
        Path path = Path.of("src/main/java/edu/hw6/Task4/files/file.txt");
        String string = "Programming is learned by writing programs. ― Brian Kernighan";
        OutputStreamManager.printToFile(path, string);

        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("Чтение из файла")
    void readFromFile() {
        Path path = Path.of("src/main/java/edu/hw6/Task4/files/file.txt");

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String expectedResult = "Programming is learned by writing programs. ― Brian Kernighan";
        assertEquals(expectedResult, result.toString());

    }
}
