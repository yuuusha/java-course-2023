package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @ParameterizedTest
    @DisplayName("Количество символов в вводе четно")
    @CsvSource({"123456, 214365", "hTsii  s aimex dpus rtni.g, This is a mixed up string."})
    void evenLengthStringTest(String text, String exceptedResult) {
        String res = Task4.fixString(text);
        assertThat(res).isEqualTo(exceptedResult);
    }

    @Test
    @DisplayName("Пустой ввод")
    void emptyInputTest() {
        String res = Task4.fixString("");
        assertThat(res).isEqualTo("");
    }

    @Test
    @DisplayName("Количество символов в вводе нечетно")
    void oddLengthStringTest() {
        String res = Task4.fixString("badce");
        assertThat(res).isEqualTo("abcde");
    }
}
