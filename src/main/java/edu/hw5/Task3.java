package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task3 {

    private static final String[] PATTERNS = {
        "yyyy-MM-dd",
        "yyyy-MM-d",
        "d/M/yyyy",
        "d/M/yy"
    };

    private Task3() {

    }

    public static Optional<LocalDate> parseDate(String string) {

        Optional<LocalDate> data = Optional.empty();

        if (Objects.equals(string, "today")) {
            data = Optional.of(LocalDate.now());
        } else if (Objects.equals(string, "tomorrow")) {
            data = Optional.of(LocalDate.now().plusDays(1));
        } else if ((Objects.equals(string, "yesterday")) || (Objects.equals(string, "1 day ago"))) {
            data = Optional.of(LocalDate.now().plusDays(-1));
        } else if (isDaysAgoPattern(string)) {
            data = Optional.of(LocalDate.now().plusDays(-Integer.parseInt(string.split(" ")[0])));
        } else {
            for (String pattern : PATTERNS) {
                try {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
                    LocalDate date = LocalDate.parse(string, format);
                    data = Optional.of(date);
                    break;
                } catch (DateTimeParseException ex) {
                    //continue;
                }
            }
        }

        return data;
    }

    private static boolean isDaysAgoPattern(String inputString) {
        String regex = "^\\d+ days ago$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

}
