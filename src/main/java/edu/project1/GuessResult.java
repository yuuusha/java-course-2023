package edu.project1;

public enum GuessResult {
    WIN("Вы выиграли!"),
    DEFEAT("Вы проиграли!"),
    SUCCESSFUL_GUESS("В яблочко!"),
    FAILED_GUESS("Промах");

    String message;
    GuessResult(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }

    public static void setAttempts(int currentAttempt, int maxAttempts) {
        FAILED_GUESS.message = String.format("Промах, ошибок %d из %d", currentAttempt, maxAttempts);
    }
}

