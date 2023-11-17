package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    static public Path dir = Paths.get("src/main/java/edu/hw6/Task3/files");

    @Test
    @DisplayName("Фильтр readable")
    void readableOk() {

        List<Path> res = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.READABLE);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(res::add);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> expectedResult = new ArrayList<>(List.of(
            Paths.get("src/main/java/edu/hw6/Task3/files/file-3.png"),
            Paths.get("src/main/java/edu/hw6/Task3/files/file2.png" ),
            Paths.get("src/main/java/edu/hw6/Task3/files/file1.txt"))
        );

        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Фильтр largerThan")
    void largerThanOk() {

        List<Path> res = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.largerThan(100_000));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(res::add);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> expectedResult = new ArrayList<>(List.of(
            Paths.get("src/main/java/edu/hw6/Task3/files/file-3.png"),
            Paths.get("src/main/java/edu/hw6/Task3/files/file2.png" ))
        );

        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Фильтр magicNumber")
    void magicNumberOk() {

        List<Path> res = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(res::add);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> expectedResult = new ArrayList<>(List.of(
            Paths.get("src/main/java/edu/hw6/Task3/files/file-3.png"),
            Paths.get("src/main/java/edu/hw6/Task3/files/file2.png" ))
        );

        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Фильтр globMatches")
    void globMatchesOk() {

        List<Path> res = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.globMatches("*.png"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(res::add);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> expectedResult = new ArrayList<>(List.of(
            Paths.get("src/main/java/edu/hw6/Task3/files/file-3.png"),
            Paths.get("src/main/java/edu/hw6/Task3/files/file2.png"))
        );

        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Фильтр regexContains")
    void regexContainsOk() {

        List<Path> res = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(res::add);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> expectedResult = new ArrayList<>(List.of(
            Paths.get("src/main/java/edu/hw6/Task3/files/file-3.png")
        ));

        assertEquals(expectedResult, res);
    }

}
