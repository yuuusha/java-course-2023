package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task3Test {
    @Test
    @DisplayName("Массив можно вложить")
    void canNestTest() {
        boolean res = Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6});
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Массив нельзя вложить")
    void canNotNestTest() {
        boolean res = Task3.isNestable(new int[]{9, 9, 8}, new int[]{8, 9});
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("Пустой первый массив")
    void emptyArray1Test() {
        Throwable thrown = catchThrowable(() -> {
            Task3.isNestable(new int[]{}, new int[]{8, 9});
        });
        assertThat(thrown).hasMessage("Некорректный ввод: пустой массив");
    }

    @Test
    @DisplayName("Пустой второй массив")
    void emptyArray2Test() {
        Throwable thrown = catchThrowable(() -> {
            Task3.isNestable(new int[]{8, 9}, null);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: пустой массив");
    }

    @Test
    @DisplayName("Пустые оба массива")
    void emptyArrayBothTest() {
        Throwable thrown = catchThrowable(() -> {
            Task3.isNestable(new int[]{}, new int[]{});
        });
        assertThat(thrown).hasMessage("Некорректный ввод: пустой массив");
    }

}
