package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Формат yyyy-MM-dd")
    void yyyyMMddTest() {
        String input = "2020-10-10";
        LocalDate expectedResult = LocalDate.parse(input);
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат yyyy-MM-d")
    void yyyyMMdTest() {
        String input = "2020-12-2";
        LocalDate expectedResult = LocalDate.parse("2020-12-02");
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат d/M/yyyy")
    void dMyyyyTest() {
        String input = "1/3/1976";
        LocalDate expectedResult = LocalDate.parse("1976-03-01");
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат d/M/yy")
    void dMyyTest() {
        String input = "1/3/20";
        LocalDate expectedResult = LocalDate.parse("2020-03-01");
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат today")
    void todayTest() {
        String input = "today";
        LocalDate expectedResult = LocalDate.now();
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат tomorrow")
    void tomorrowTest() {
        String input = "tomorrow";
        LocalDate expectedResult = LocalDate.now().plusDays(1);
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат yesterday")
    void yesterdayTest() {
        String input = "yesterday";
        LocalDate expectedResult = LocalDate.now().plusDays(-1);
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат day ago")
    void dayAgoTest() {
        String input = "1 day ago";
        LocalDate expectedResult = LocalDate.now().plusDays(-1);
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Формат days ago")
    void daysAgoTest() {
        String input = "2234 days ago";
        LocalDate expectedResult = LocalDate.now().plusDays(-2234);
        Optional<LocalDate> res = Task3.parseDate(input);
        assertEquals(expectedResult, res.get());
    }

    @Test
    @DisplayName("Ни один формат не подошел")
    void incorrectInputTest() {
        assertEquals(Optional.empty(), Task3.parseDate("274186-12084918"));
    }
}
