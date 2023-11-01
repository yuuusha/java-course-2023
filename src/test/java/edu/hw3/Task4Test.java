package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Число из диапазона от 1 до 3999")
    @CsvSource({"2, II", "12, XII", "16, XVI"})
    void numberFromRangeTest(Integer inputNumber, String expectedResult) {
        String res = Task4.convertToRoman(inputNumber);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Число больше 3999")
    void numberMoreThanRangeTest() {
        Throwable thrown = catchThrowable(() -> {
            Task4.convertToRoman(5000);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число вне диапазона от 1 до 3999");
    }

    @Test
    @DisplayName("Число меньше 1")
    void numberLessThanRangeTest() {
        Throwable thrown = catchThrowable(() -> {
            Task4.convertToRoman(0);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число вне диапазона от 1 до 3999");
    }

    @Test
    @DisplayName("В числе присутствует правило вычитания (меньшая цифра записана справа от большей")
    void numberSubRuleTest() {
        String res = Task4.convertToRoman(49);
        assertEquals("XLIX", res);
    }
}
