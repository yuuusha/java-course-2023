package edu.hw8;

import edu.hw8.Task2.FibonacciCalculator;
import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Вычисление числа Фибоначчи многопоточно")
    void fibonacciCalculateMultithread() {
        try (FixedThreadPool threadPool = FixedThreadPool.create(4)) {
            threadPool.start();

            for (int i = 0; i < 10; i++) {
                final int index = i;
                threadPool.execute(() -> {
                    FibonacciCalculator.calculateFibonacci(index);
                });
            }

            Thread.sleep(2000);
            long expectedResult = 34;
            long result = FibonacciCalculator.FIBONACCI_CACHE.get(9);
            assertEquals(expectedResult, result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
