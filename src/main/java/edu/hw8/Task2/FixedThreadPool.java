package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int numThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.threads = new Thread[numThreads];
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (int i = 0; i < numThreads; i++) {
            threads[i].interrupt();
        }
    }

    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }
}

