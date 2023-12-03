package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    @DisplayName("Длина строки больше 3 и третий символ 0")
    void lengthMoreThan3And0Test() {
        String input = "110101001";
        assertTrue(Task7.lengthThreeAndZero(input));
    }

    @Test
    @DisplayName("Длина меньше 3")
    void lengthLessThan3Test() {
        String input = "10";
        assertFalse(Task7.lengthThreeAndZero(input));
    }

    @Test
    @DisplayName("Длина больше 3 и третий символ не 0")
    void lengthMoreThan3AndNot0Test() {
        String input = "101011010";
        assertFalse(Task7.lengthThreeAndZero(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput1Test() {
        String input = "10001a1010";
        assertFalse(Task7.lengthThreeAndZero(input));
    }


    @Test
    @DisplayName("Строка начинается и заканчивается одним и тем же символом")
    void sameStartEndTest() {
        String input = "100011011";
        assertTrue(Task7.sameStartEnd(input));
    }

    @Test
    @DisplayName("Строка не начинается и заканчивается одним и тем же символом")
    void notSameStartEndTest() {
        String input = "100011010";
        assertFalse(Task7.sameStartEnd(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput2Test() {
        String input = "10b01";
        assertFalse(Task7.sameStartEnd(input));
    }

    @Test
    @DisplayName("Длина строки от 1 до 3")
    void lengthBetween1and3Test() {
        String input = "100";
        assertTrue(Task7.lengthBetween1and3(input));
    }

    @Test
    @DisplayName("Длина строки больше 3")
    void lengthMoreThan3Test() {
        String input = "100011010";
        assertFalse(Task7.lengthBetween1and3(input));
    }

    @Test
    @DisplayName("Длина строки меньше 1")
    void lengthLessThan1Test() {
        String input = "";
        assertFalse(Task7.lengthBetween1and3(input));
    }

    @Test
    @DisplayName("В строке лишние символы")
    void incorrectInput3Test() {
        String input = "10b";
        assertFalse(Task7.lengthBetween1and3(input));
    }
}
