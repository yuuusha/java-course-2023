package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {

    private Task1() {

    }

    @SuppressWarnings("MagicNumber")
    public static int incrementer() {

        AtomicInteger count = new AtomicInteger(0);
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.incrementAndGet();
                }
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return count.get();
    }

}
