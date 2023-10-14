package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task8Test {
    @Test
    @DisplayName("Ни один конь не бьет другого")
    void knightsDoNotAttackTest() {
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        boolean res = Task8.knightBoardCapture(board);
        assertThat(res).isEqualTo(true);
    }

    @Test
    @DisplayName("Хотя бы один конь бьет другого")
    void knightsAttackTest() {
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        boolean res = Task8.knightBoardCapture(board);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("Строк в массиве не 8")
    void rowsOfBoardLessThen8Test() {
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
        };
        Throwable thrown = catchThrowable(() -> {
            Task8.knightBoardCapture(board);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: размерность массива не равна 8х8");
    }

    @Test
    @DisplayName("Столбцов в массиве не 8")
    void columnsOfBoardLessThen8Test() {
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0}
        };
        Throwable thrown = catchThrowable(() -> {
            Task8.knightBoardCapture(board);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: размерность массива не равна 8х8");
    }

    @Test
    @DisplayName("Некорректные значения в массиве")
    void incorrectValueTest() {
        int[][] board = {
            {0, 0, 0, 0, 1, 8, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 8},
            {0, 0, 4, 1, 0, 0, 0, 3},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 7, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 9, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 9, 0, 1}
        };
        Throwable thrown = catchThrowable(() -> {
            Task8.knightBoardCapture(board);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: значение массива, отличное от 0 или 1");
    }
}
