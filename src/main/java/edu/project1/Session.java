package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    public Session(String answer, char[] userAnswer, int maxAttempts, int attempts) {
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.maxAttempts = maxAttempts;
        this.attempts = attempts;
    }

    @NotNull GuessResult guess(char inputChar) {

        int i = 0;
        boolean answerContainsChar = false;
        while (answer.indexOf(inputChar, i) != -1) {
            userAnswer[answer.indexOf(inputChar, i)] = inputChar;
            i = answer.indexOf(inputChar, i) + 1;
            answerContainsChar = true;
        }

        if (!answerContainsChar) {
            attempts++;
            if (attempts == maxAttempts) {
                return new GuessResult.Defeat(userAnswer, attempts, maxAttempts);
            }
            return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
        }

        if (Arrays.equals(userAnswer, answer.toCharArray())) {
            return new GuessResult.Win(userAnswer, attempts, maxAttempts);
        }

        return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts);
    }

}
