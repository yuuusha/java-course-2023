package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public final class Task1 {

    private static final String PATTERN = "yyyy-MM-dd, HH:mm";

    private Task1() {

    }

    public static String compClubClientsAverageTime(List<String> inputTime) throws DateTimeParseException {

        if (inputTime.isEmpty()) {
            return null;
        }

        Duration totalTime = Duration.ZERO;

        for (String input : inputTime) {
            String[] timeArray = input.split(" - ");
            if (timeArray.length != 2) {
                throw new RuntimeException("Неверный формат ввода");
            }
            LocalDateTime startTime =
                LocalDateTime.parse(timeArray[0], DateTimeFormatter.ofPattern(PATTERN));
            LocalDateTime endTime =
                LocalDateTime.parse(timeArray[1], DateTimeFormatter.ofPattern(PATTERN));

            Duration duration = Duration.between(startTime, endTime);
            totalTime = totalTime.plus(duration);
        }

        totalTime = totalTime.dividedBy(inputTime.size());
        return totalTime.toHours() + "ч " + totalTime.toMinutesPart() + "м";
    }
}
