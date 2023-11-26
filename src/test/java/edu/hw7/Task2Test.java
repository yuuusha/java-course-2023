package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Вычисление факториала параллельно")
    void factorialParallelTest() {
        assertEquals(362880, Task2.factorial(9));
    }

    @Test
    @DisplayName("Передали отрицательное число")
    void negativeFactorialParallelTest() {
        Throwable thrown = catchThrowable(() -> {
            Task2.factorial(-1);
        });
        assertThat(thrown).hasMessage("Число отрицательное");
    }

}
