package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @ParameterizedTest
    @DisplayName("Количество символов в вводе четно")
    @CsvSource({"123456, 214365", "hTsii  s aimex dpus rtni.g, This is a mixed up string."})
    void evenLengthStringTest(String text, String expectedResult) {
        String res = Task4.fixString(text);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Пустой ввод")
    void emptyInputTest() {
        String res = Task4.fixString("");
        assertEquals("", res);
    }

    @Test
    @DisplayName("Количество символов в вводе нечетно")
    void oddLengthStringTest() {
        String res = Task4.fixString("badce");
        assertEquals("abcde", res);
    }
}
