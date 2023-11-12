package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Пятницы 13 этого года")
    void fridayOf13Test() {
        List<LocalDate> res = Task2.fridayOf13(1925);
        List<LocalDate> expectedResult = new ArrayList<>(List.of(LocalDate.parse("1925-02-13"),
            LocalDate.parse("1925-03-13"),
            LocalDate.parse("1925-11-13")));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Следующая пятница 13")
    void nextFridayOf13Test() {
        LocalDate res = Task2.nextFridayOf13(LocalDate.parse("1925-02-14"));
        LocalDate expectedResult = LocalDate.parse("1925-03-13");
        assertEquals(expectedResult, res);
    }
}
