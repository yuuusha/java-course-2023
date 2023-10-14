package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task6Test {
    @ParameterizedTest
    @DisplayName("Число состоит из четырех знаков")
    @CsvSource({"6621, 5", "6554, 4", "1234, 3"})
    void fourDigitTest(int number, int expectedResult) {
        int res = Task6.stepsOfK(number);
        assertThat(res).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Число состоит менее чем из четырех знаков")
    void lessThanFourDigitTest() {
        Throwable thrown = catchThrowable(() -> {
            Task6.stepsOfK(999);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число не является четырехзначным");
    }

    @Test
    @DisplayName("Число состоит более чем из четырех знаков")
    void moreThenFourTest() {
        Throwable thrown = catchThrowable(() -> {
            Task6.stepsOfK(12345);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число не является четырехзначным");
    }

    @Test
    @DisplayName("Разность Капрекара менее 1000")
    void containsZeroTest() {
        int res = Task6.stepsOfK(1000);
        assertThat(res).isEqualTo(5);
    }

    @Test
    @DisplayName("Число состоит из одинаковых цифр")
    void notUniqDigitsTest() {
        Throwable thrown = catchThrowable(() -> {
            Task6.stepsOfK(1111);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число не должно состоять из одинаковых цифр");
    }
}

