package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    @Test
    @DisplayName("Вычисление Pi однопоточно")
    void calculatePiSingleThreadTest() {
        double pi = Task4.calculatePiSingleThread(10_000_000);
        assertTrue(pi > 3.13 && pi < 3.15);
    }

    @Test
    @DisplayName("Вычисление Pi многопоточно")
    void calculatePiMultiThreadTest() {
        double pi = Task4.calculatePiMultiThread(10_000_000);
        assertTrue(pi > 3.13 && pi < 3.15);
    }

    @Test
    @DisplayName("Многопоточный быстрее однопоточного")
    void durationTimeTest() {
        long n = 10_000_000;

        long time1 = System.nanoTime();
        Task4.calculatePiSingleThread(n);
        long dur1 = System.nanoTime() - time1;

        long time2 = System.nanoTime();
        Task4.calculatePiMultiThread(n);
        long dur2 = System.nanoTime() - time2;

        assertTrue(dur2 < dur1);
    }
}
