package edu.project1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleHangman {

    private ConsoleHangman() {

    }

    private static final int MAX_ATTEMPTS = 5;
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("ReturnCount")
    public static void run() {

        LOGGER.info("Для выхода из игры пропишите \"exit\" ");

        String answer = Dictionary.randomWord();
        char[] userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
        int attempts = 0;

        String inputString;
        LOGGER.info(Arrays.toString(userAnswer));
        while (attempts < MAX_ATTEMPTS) {
            LOGGER.info("Введите букву:");
            inputString = SCANNER.nextLine();

            if (Objects.equals(inputString, "exit")) {
                return;
            }
            if (inputString.length() != 1) {
                LOGGER.error("Должна быть введена лишь одна буква. Попытайтесь снова.");
                continue;
            }
            char inputChar = Character.toLowerCase(inputString.charAt(0));
            if (!Character.UnicodeBlock.of(inputChar).equals(Character.UnicodeBlock.CYRILLIC)) {
                LOGGER.error("Должна быть введена буква кириллического алфавита. Попытайтесь снова.");
                continue;
            }

            Session session = new Session(answer, userAnswer, MAX_ATTEMPTS, attempts);
            GuessResult guessResult = session.guess(inputChar);
            attempts = guessResult.attempt();
            userAnswer = guessResult.state();
            printState(guessResult);
            if (guessResult instanceof GuessResult.Win || guessResult instanceof GuessResult.Defeat) {
                return;
            }
        }
    }

    private static void printState(GuessResult guess) {
        LOGGER.info(guess.message());
        if (!(guess instanceof GuessResult.Win || guess instanceof GuessResult.Defeat)) {
            LOGGER.info("");
            LOGGER.info("Слово: " + Arrays.toString(guess.state()));
        }
    }

}
