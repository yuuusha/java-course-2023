package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final Map<String, List<Double>> dataMap;
    private final ExecutorService executor;

    public StatsCollector() {
        this.dataMap = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void push(String metricName, double[] values) {
        //synchronized (dataMap) {
            dataMap.computeIfAbsent(metricName, k -> new ArrayList<>()).addAll(toList(values));
        //}
    }

    public List<StatsResult> stats() {
        List<StatsResult> results = new ArrayList<>();

        CountDownLatch latch = new CountDownLatch(dataMap.size());

        for (Map.Entry<String, List<Double>> entry : dataMap.entrySet()) {
            String metricName = entry.getKey();
            List<Double> values = entry.getValue();

            executor.submit(() -> {
                double sum = calculateSum(values);
                double average = calculateAverage(values);
                double max = calculateMax(values);
                double min = calculateMin(values);

                StatsResult result = new StatsResult(metricName, sum, average, max, min);
                synchronized (results) {
                    results.add(result);
                }

                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return results;
    }

    private List<Double> toList(double[] values) {
        List<Double> list = new ArrayList<>();
        for (double value : values) {
            list.add(value);
        }
        return list;
    }

    private double calculateSum(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }

    private double calculateAverage(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private double calculateMax(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
    }

    private double calculateMin(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
    }
}
