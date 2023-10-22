package edu.project1;

import org.jetbrains.annotations.NotNull;

public interface GuessResult {

    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return "Вы проиграли!";
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return "Вы выиграли!";
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return "В яблочко!";
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        public @NotNull String message() {
            return "Промах, ошибок " + attempt() + " из " + maxAttempts() + ".";
        }
    }
}
