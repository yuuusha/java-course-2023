package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CloneFile {

    private CloneFile() {

    }

    public static void cloneFile(Path path) {

        if (!Files.exists(path)) {
            throw new RuntimeException("Запрашиваемого файла не существует");
        }

        try {
            String fileName = path.getFileName().toString();
            String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            Path destinationPath = path.getParent();

            int copyNumber = 1;
            Path destinationFile = destinationPath.resolve(fileName);

            while (Files.exists(destinationFile)) {
                String copySuffix = (copyNumber == 1) ? " — копия" : " — копия (" + copyNumber + ")";
                String newFileName = baseName + copySuffix + extension;
                destinationFile = destinationPath.resolve(newFileName);
                copyNumber++;
            }

            Files.copy(path, destinationFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
