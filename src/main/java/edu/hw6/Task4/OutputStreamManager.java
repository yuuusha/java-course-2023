package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class OutputStreamManager {

    private OutputStreamManager() {

    }

    public static void printToFile(Path filePath, String stringToWrite) {

        try (OutputStream fileOutputStream = Files.newOutputStream(filePath);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter =
                 new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
                printWriter.println(stringToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
