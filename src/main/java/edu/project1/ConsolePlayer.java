package edu.project1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsolePlayer implements Player {

    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Logger LOGGER = LogManager.getLogger();

    public char getChar() throws ExitException {

        while (true) {
            String inputString = SCANNER.nextLine();
            char inputChar;

            if (Objects.equals(inputString, "exit")) {
                throw new ExitException("Выход из игры...");
            }

            if (inputString.length() != 1) {
                LOGGER.error("Должна быть введена лишь одна буква. Попытайтесь снова.");
                continue;
            }

            inputChar = Character.toLowerCase(inputString.charAt(0));
            if (!Character.UnicodeBlock.of(inputChar).equals(Character.UnicodeBlock.CYRILLIC)) {
                LOGGER.error("Должна быть введена буква кириллического алфавита. Попытайтесь снова.");
                continue;
            }

            return inputChar;
        }
    }

    public void printMessage(String message) {
        LOGGER.info(message);
    }

}
