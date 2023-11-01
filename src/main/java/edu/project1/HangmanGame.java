package edu.project1;

import java.util.Arrays;

public class HangmanGame {

    public Player player;
    public Board board;

    public HangmanGame(Player player) {

        this.player = player;
        this.board = new Board(Dictionary.randomWord());
    }

    public void run() {

        player.printMessage("Для выхода из игры пропишите \"exit\"");

        while (board.attempts < board.maxAttempts) {
            player.printMessage(Arrays.toString(board.userAnswer));
            player.printMessage("Введите букву:");

            char inputChar;
            try {
                inputChar = player.getChar();
            } catch (ExitException ex) {
                player.printMessage(ex.getMessage());
                return;
            }

            GuessResult guessResult = board.guess(inputChar);
            player.printMessage(guessResult.getMessage());

            if (guessResult == GuessResult.WIN || guessResult == GuessResult.DEFEAT) {
                break;
            }
        }

    }

}
