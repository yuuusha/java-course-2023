package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {

    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> fridayOf13(int year) {
        List<LocalDate> fridayOf13List = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            LocalDate dateForFriday = LocalDate.of(year, month, 13);
            if (dateForFriday.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridayOf13List.add(dateForFriday);
            }
        }

        return fridayOf13List;
    }

    @SuppressWarnings("MagicNumber")
    public static LocalDate nextFridayOf13(LocalDate currentFridayOf13) {
        LocalDate nextFridayOf13 = currentFridayOf13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFridayOf13.getDayOfMonth() != 13) {
            nextFridayOf13 = nextFridayOf13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return nextFridayOf13;
    }
}
