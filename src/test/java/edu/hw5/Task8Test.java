package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {

    @Test
    @DisplayName("Длина строки нечетная")
    void oddLengthTest() {
        String input = "11010100011";
        assertTrue(Task8.oddLength(input));
    }

    @Test
    @DisplayName("Длина строки четная")
    void evenLengthTest() {
        String input = "1101010001";
        assertFalse(Task8.oddLength(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput1Test() {
        String input = "110101vg0001";
        assertFalse(Task8.oddLength(input));
    }

    @Test
    @DisplayName("Начинается с 0 и нечетная длина")
    void oddLengthWhen0Test() {
        String input = "01010";
        assertTrue(Task8.oddLengthWhen0AndEvenLengthWhen1(input));
    }

    @Test
    @DisplayName("Начинается с 0 и четная длина")
    void evenLengthWhen0Test() {
        String input = "0101";
        assertFalse(Task8.oddLengthWhen0AndEvenLengthWhen1(input));
    }

    @Test
    @DisplayName("Начинается с 1 и нечетная длина")
    void oddLengthWhen1Test() {
        String input = "101";
        assertFalse(Task8.oddLengthWhen0AndEvenLengthWhen1(input));
    }

    @Test
    @DisplayName("Начинается с 1 и четная длина")
    void evenLengthWhen1Test() {
        String input = "1011";
        assertTrue(Task8.oddLengthWhen0AndEvenLengthWhen1(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput2Test() {
        String input = "10s11";
        assertFalse(Task8.oddLengthWhen0AndEvenLengthWhen1(input));
    }

    @Test
    @DisplayName("Каждый нечетный символ единица")
    void everyOddSymbolIs1Test() {
        String input = "10101011";
        assertTrue(Task8.everyOddSymbolIs1(input));
    }

    @Test
    @DisplayName("Не каждый нечетный символ единица")
    void notEveryOddSymbolIs1Test() {
        String input = "10001011";
        assertFalse(Task8.everyOddSymbolIs1(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput3Test() {
        String input = "10s11";
        assertFalse(Task8.everyOddSymbolIs1(input));
    }

    @Test
    @DisplayName("Строка 11")
    void input11Test() {
        String input = "11";
        assertFalse(Task8.allExcept11And111(input));
    }

    @Test
    @DisplayName("Строка 111")
    void input111Test() {
        String input = "111";
        assertFalse(Task8.allExcept11And111(input));
    }

    @Test
    @DisplayName("Любая кроме 11 или 111")
    void except11And111Test() {
        String input = "1101";
        assertTrue(Task8.allExcept11And111(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput4Test() {
        String input = "10s11";
        assertFalse(Task8.allExcept11And111(input));
    }

}
