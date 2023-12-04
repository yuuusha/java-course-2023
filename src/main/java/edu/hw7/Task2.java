package edu.hw7;

import java.util.stream.LongStream;

public final class Task2 {

    private Task2() {

    }

    public static long factorial(int number) {
        if (number < 0) {
            throw new RuntimeException("Число отрицательное");
        }
        return LongStream.rangeClosed(1, number)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}
