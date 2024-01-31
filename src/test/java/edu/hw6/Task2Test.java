package edu.hw6;

import edu.hw3.Task2;
import edu.hw6.Task2.CloneFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {

    @Test
    @DisplayName("Создание копии")
    void cloneFileTest() {
        Path filePath = Paths.get("src/main/resources/hw6/Task2/Tinkoff Bank Biggest Secret.txt");
        Path resultPath = Paths.get("src/main/resources/hw6/Task2/Tinkoff Bank Biggest Secret — копия (3).txt");

        CloneFile.cloneFile(filePath);

        assertTrue(Files.exists(resultPath));
    }

    @Test
    @DisplayName("Файл не существует")
    void fileNotExistTest() {
        Path filePath = Paths.get("src/main/resources/hw6/Task2/file.txt");

        Throwable thrown = catchThrowable(() -> {
            CloneFile.cloneFile(filePath);
        });
        assertThat(thrown).hasMessage("Запрашиваемого файла не существует");

    }

}
