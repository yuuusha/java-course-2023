package edu.hw7;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public final class Task4 {

    private static final Random RANDOM = new Random();

    private Task4() {

    }

    @SuppressWarnings("MagicNumber")
    public static double calculatePiSingleThread(long n) {
        long totalCount = 0;
        long circleCount = 0;

        for (int i = 0; i < n; i++) {
            double x = RANDOM.nextDouble();
            double y = RANDOM.nextDouble();

            double r = Math.sqrt(x * x + y * y);

            if (r <= 1) {
                circleCount++;
            }

            totalCount++;
        }

        return 4.0 * circleCount / totalCount;
    }

    @SuppressWarnings("MagicNumber")
    public static double calculatePiMultiThread(long n) {

        int numOfThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
        long numOfWorksForThread = n / numOfThreads;

        Callable<Long> work = () -> {
            long circleCountLocalThread = 0;

            for (int i = 0; i < numOfWorksForThread; i++) {
                double x = ThreadLocalRandom.current().nextDouble();
                double y = ThreadLocalRandom.current().nextDouble();

                double r = Math.sqrt(x * x + y * y);

                if (r <= 1) {
                    circleCountLocalThread++;
                }
            }

            return circleCountLocalThread;
        };

        var tasks = Stream.generate(() -> work).limit(2).toList();

        long circleCountTotal = 0;

        try {
            List<Future<Long>> futures = executorService.invokeAll(tasks);
            circleCountTotal = futures
                .stream()
                .mapToLong(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sum();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 4.0 * circleCountTotal / n;
    }

}
