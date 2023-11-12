package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {

    @Test
    @DisplayName("Строка является подстрокой")
    void isSubstringTest() {
        String subString = "abc";
        String mainString = "achfdbaabgabcaabg";
        assertTrue(Task6.isSubstring(mainString, subString));
    }

    @Test
    @DisplayName("Строка не является подстрокой")
    void isNotSubstringTest() {
        String subString = "abc";
        String mainString = "qwerty";
        assertFalse(Task6.isSubstring(mainString, subString));
    }
}
