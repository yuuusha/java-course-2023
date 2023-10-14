package edu.hw1;

public final class Task1 {

    private Task1() {

    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String inputString) throws RuntimeException {

        if (!inputString.contains(":")) {
            throw new RuntimeException("Некорректный ввод: отсутствие знака двоеточия");
        }

        String[] minutesAndSeconds = inputString.split(":");

        if (minutesAndSeconds.length != 2) {
            throw new RuntimeException("Некорректный ввод: знак двоеточия присутствует более одного раза");
        }

        int minutes;
        int seconds;

        try {
            minutes = Integer.parseInt(minutesAndSeconds[0]);
            seconds = Integer.parseInt(minutesAndSeconds[1]);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Некорректный ввод: невозможно обработать ввод");
        }

        if (seconds >= 60) {
            return -1;
        }

        return minutes * 60 + seconds;
    }
}
