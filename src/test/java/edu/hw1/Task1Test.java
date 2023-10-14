package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Секунд меньше чем 60")
    @CsvSource({"13:56, 836", "01:00, 60"})
    void secondsLessThan60Test(String time, int expectedResult) {
        int res = Task1.minutesToSeconds(time);
        assertThat(res).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Секунд больше чем 60")
    void secondMoreThan60Test() {
        int res = Task1.minutesToSeconds("10:60");
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отсутствие знака двоеточия")
    void noColonSign() {
        Throwable thrown = catchThrowable(() -> {
            Task1.minutesToSeconds("1345");
        });
        assertThat(thrown).hasMessage("Некорректный ввод: отсутствие знака двоеточия");
    }

    @Test
    @DisplayName("Знак двоеточия присутствует более одного раза")
    void colonSignMoreThan1() {
        Throwable thrown = catchThrowable(() -> {
            Task1.minutesToSeconds("13:4:5");
        });
        assertThat(thrown).hasMessage("Некорректный ввод: знак двоеточия присутствует более одного раза");
    }

    @Test
    @DisplayName("Невозможно обработать ввод")
    void unableToProcessInput() {
        Throwable thrown = catchThrowable(() -> {
            Task1.minutesToSeconds("1a3:4b5");
        });
        assertThat(thrown).hasMessage("Некорректный ввод: невозможно обработать ввод");
    }
}
