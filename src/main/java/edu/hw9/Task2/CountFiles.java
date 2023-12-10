package edu.hw9.Task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CountFiles extends RecursiveTask<List<Path>> {
    private final File directory;
    private final int k;

    public CountFiles(File directory, int k) {
        this.directory = directory;
        this.k = k;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();

        File[] files = directory.listFiles();

        if (files != null) {
            List<CountFiles> subTasks = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    CountFiles subTask = new CountFiles(file, k);
                    subTask.fork();
                    subTasks.add(subTask);
                }
            }

            for (CountFiles subTask : subTasks) {
                result.addAll(subTask.join());
            }

            if (files.length > k) {
                result.add(Path.of(directory.getPath()));
            }
        }

        return result;
    }

    public static List<Path> findDirectoriesWithKFiles(String root, int k) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        File rootDir = new File(root);
        return forkJoinPool.invoke(new CountFiles(rootDir, k));
    }
}
