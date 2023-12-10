package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class SearchFiles extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    SearchFiles(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<SearchFiles> subtasks = new ArrayList<>();
        List<File> result = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    SearchFiles subtask = new SearchFiles(file, predicate);
                    subtasks.add(subtask);
                    subtask.fork();
                } else {
                    if (predicate.test(file)) {
                        result.add(file);
                    }
                }
            }
        }

        for (SearchFiles subtask : subtasks) {
            result.addAll(subtask.join());
        }

        return result;
    }

    public static List<File> searchByPredicate(String root, Predicate<File> predicate) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        File rootDir = new File(root);
        return forkJoinPool.invoke(new SearchFiles(rootDir, predicate));
    }

}
