package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Валидные номера")
    @CsvSource({"А123ВЕ777", "О777ОО177"})
    void validCarNumbersTest(String carNumber) {
        assertTrue(Task5.carNumberIsValid(carNumber));
    }

    @ParameterizedTest
    @DisplayName("Не валидные номера")
    @CsvSource({"123АВЕ777", "А123ВГ77", "А123ВЕ7777"})
    void notValidCarNumbersTest(String carNumber) {
        assertFalse(Task5.carNumberIsValid(carNumber));
    }
}
