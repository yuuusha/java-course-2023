package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {
    @ParameterizedTest
    @DisplayName("Поворот влево положительного числа на положительный сдвиг")
    @CsvSource({"16, 1, 1", "17, 2, 6"})
    void rotateLeftCorrectTest(int number, int shift, int expectedResult) {
        int res = Task7.rotateLeft(number, shift);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Поворот вправо положительного числа на положительный сдвиг")
    void rotateRightCorrectTest() {
        int res = Task7.rotateRight(8,1);
        assertEquals(4, res);
    }

    @Test
    @DisplayName("Поворот влево положительного числа на отрицательный сдвиг")
    void rotateLeftNegativeShiftTest() {
        int res = Task7.rotateLeft(8,-1);
        assertEquals(4, res);
    }

    @Test
    @DisplayName("Поворот вправо положительного числа на отрицательный сдвиг")
    void rotateRightNegativeShiftTest() {
        int res = Task7.rotateRight(8,-1);
        assertEquals(1, res);
    }

    @Test
    @DisplayName("Поворот влево положительного числа на сдвиг, больший длины числа")
    void rotateLeftLongShiftTest() {
        int res = Task7.rotateLeft(8,20);
        assertEquals(8, res);
    }

    @Test
    @DisplayName("Поворот вправо положительного числа на сдвиг, больший длины числа")
    void rotateRightLongShiftTest() {
        int res = Task7.rotateRight(8,20);
        assertEquals(8, res);
    }

    @Test
    @DisplayName("Поворот влево неположительного числа")
    void rotateLeftNegativeNumberTest() {
        Throwable thrown = catchThrowable(() -> {
            Task7.rotateLeft(-8, 1);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число неположительное");
    }

    @Test
    @DisplayName("Поворот вправо неположительного числа")
    void rotateRightNegativeNumberTest() {
        Throwable thrown = catchThrowable(() -> {
            Task7.rotateRight(0, 1);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число неположительное");
    }

}
