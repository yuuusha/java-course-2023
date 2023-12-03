package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {

    @Test
    @DisplayName("Пароль содержит символы")
    void passwordContainsTest() {
        String password = "abcde!";
        assertTrue(Task4.passwordContains(password));
    }

    @Test
    @DisplayName("Пароль не содержит символы")
    void passwordNotContainsTest() {
        String password = "abcde";
        assertFalse(Task4.passwordContains(password));
    }
}
