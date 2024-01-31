package edu.hw9;

import edu.hw9.Task2.CountFiles;
import edu.hw9.Task2.SearchFiles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {

    @Test
    @DisplayName("Директория с k файлами")
    void isMoreKFilesInDirectoryTest() {
        String root = "src/main/resources/hw9/Task2";
        int k = 2;
        List<Path> listFiles = CountFiles.findDirectoriesWithKFiles(root, k);
        assertEquals(2, listFiles.size());
    }

    @Test
    @DisplayName("Поиск по предикату")
    void searchByPredicateTest() {
        String root = "src/main/resources/hw9/Task2";
        Predicate<File> isPngFile = file -> file.isFile() && file.getName().toLowerCase().endsWith(".png");
        List<File> listFile = SearchFiles.searchByPredicate(root, isPngFile);
        assertEquals(1, listFile.size());
    }

}
