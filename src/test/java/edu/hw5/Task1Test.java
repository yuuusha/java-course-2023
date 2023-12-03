package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Среднее время")
    void averageTimeTest() {
        List<String> inputTime = new ArrayList<>(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"));
        String res = Task1.compClubClientsAverageTime(inputTime);
        assertEquals("3ч 40м", res);
    }

    @Test
    @DisplayName("Неверный формат ввода")
    void incorrectInputTest() {
        List<String> inputTime = new ArrayList<>(List.of("2022-03-12, 20:20 2022-03-12, 23:50",
            "2022-04-01, 21:30 2022-04-02, 01:20"));

        Throwable thrown = catchThrowable(() -> {
            Task1.compClubClientsAverageTime(inputTime);
        });
        assertThat(thrown).hasMessage("Неверный формат ввода");
    }

    @Test
    @DisplayName("Неверный паттерн")
    void incorrectPatternTest() {
        List<String> inputTime = new ArrayList<>(List.of("20 - 202",
            "202 - 01:20"));

        assertThrows(DateTimeParseException.class, () -> {
            Task1.compClubClientsAverageTime(inputTime);
        });
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        List<String> inputTime = new ArrayList<>();

        String res = Task1.compClubClientsAverageTime(inputTime);
        assertNull(res);
    }

}
