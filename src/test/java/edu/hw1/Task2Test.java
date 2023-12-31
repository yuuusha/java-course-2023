package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @ParameterizedTest
    @DisplayName("Обычное поведение")
    @CsvSource({"4666, 4", "544, 3"})
    void normalTest(int number, int expectedResult) {
        int res = Task2.countDigits(number);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Проверка цифры 0")
    void zeroTest() {
        int res = Task2.countDigits(0);
        assertEquals(1, res);
    }

    @Test
    @DisplayName("Проверка отрицательного числа")
    void negativeNumberTest() {
        int res = Task2.countDigits(-103);
        assertEquals(3, res);
    }
}
