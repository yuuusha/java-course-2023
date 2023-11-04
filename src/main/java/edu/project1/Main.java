package edu.project1;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {

        Player consolePlayer = new ConsolePlayer();
        HangmanGame hangmanGame = new HangmanGame(consolePlayer);
        hangmanGame.run();

    }
}
