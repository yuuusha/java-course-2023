package edu.hw8.Task2;

import java.util.concurrent.ConcurrentHashMap;

public class FibonacciCalculator {

    private FibonacciCalculator() {

    }

    public static final ConcurrentHashMap<Integer, Long> FIBONACCI_CACHE = new ConcurrentHashMap<>();

    public static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return FIBONACCI_CACHE.computeIfAbsent(n, k -> calculateFibonacci(n - 1) + calculateFibonacci(n - 2));
        }
    }
}

