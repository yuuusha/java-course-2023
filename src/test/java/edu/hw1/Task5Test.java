package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task5Test {
    @ParameterizedTest
    @DisplayName("Количество цифр четно - результат true")
    @CsvSource({"11211230", "13001120", "23336014"})
    void evenTrueTest(int number) {
        boolean res = Task5.isPalindromeDescendant(number);
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Количество цифр четно - результат false")
    void evenFalseTest() {
        boolean res = Task5.isPalindromeDescendant(123456);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("Количество цифр нечетно - результат true")
    void oddTrueTest() {
        boolean res = Task5.isPalindromeDescendant(134);
        assertThat(res).isEqualTo(true);
    }

    @Test
    @DisplayName("Количество цифр нечетно - результат false")
    void oddFalseTest() {
        boolean res = Task5.isPalindromeDescendant(233360146);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("Число уже является палиндромом")
    void alreadyPalindromeTest() {
        boolean res = Task5.isPalindromeDescendant(11);
        assertThat(res).isEqualTo(true);
    }

    @Test
    @DisplayName("Число состоит из одной цифры")
    void digitTest() {
        boolean res = Task5.isPalindromeDescendant(1);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("Число отрицательное")
    void negativeTest() {
        Throwable thrown = catchThrowable(() -> {
            Task5.isPalindromeDescendant(-123321);
        });
        assertThat(thrown).hasMessage("Некорректный ввод: число отрицательное");
    }
}
