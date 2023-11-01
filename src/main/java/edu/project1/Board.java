package edu.project1;

import java.util.Arrays;

public class Board {

    public final int maxAttempts = 5;
    public int attempts;
    public String answer;
    public char[] userAnswer;

    public Board(String answer) {
        this.answer = answer;
        this.attempts = 0;
        userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
    }

    public GuessResult guess(char inputChar) {

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
                return GuessResult.DEFEAT;
            }
            GuessResult.setAttempts(attempts, maxAttempts);
            return GuessResult.FAILED_GUESS;
        }

        if (Arrays.equals(userAnswer, answer.toCharArray())) {
            return GuessResult.WIN;
        }

        return GuessResult.SUCCESSFUL_GUESS;
    }

}
