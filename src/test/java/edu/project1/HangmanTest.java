package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanTest {
    @Test
    @DisplayName("Успешное угадывание")
    void successfulGuessTest() {
        RandomSeed.random.setSeed(53);
        Player ConsolePlayer = new ConsolePlayer();
        HangmanGame hangmanGame = new HangmanGame(ConsolePlayer);
        GuessResult guessResult = hangmanGame.board.guess('а');

        assertEquals(Arrays.toString(hangmanGame.board.userAnswer), "[а, *, *, *, *]");
        assertEquals(hangmanGame.board.attempts, 0);
        assertEquals(guessResult, GuessResult.SUCCESSFUL_GUESS);
    }

    @Test
    @DisplayName("Неудачное угадывание")
    void failedGuessTest() {
        RandomSeed.random.setSeed(53);
        Player ConsolePlayer = new ConsolePlayer();
        HangmanGame hangmanGame = new HangmanGame(ConsolePlayer);
        GuessResult guessResult = hangmanGame.board.guess('ы');

        assertEquals(Arrays.toString(hangmanGame.board.userAnswer), "[*, *, *, *, *]");
        assertEquals(hangmanGame.board.attempts, 1);
        assertEquals(guessResult, GuessResult.FAILED_GUESS);
    }

    @Test
    @DisplayName("Победа")
    void winTest() {
        RandomSeed.random.setSeed(53);
        Player ConsolePlayer = new ConsolePlayer();
        HangmanGame hangmanGame = new HangmanGame(ConsolePlayer);
        hangmanGame.board.guess('а');
        hangmanGame.board.guess('р');
        hangmanGame.board.guess('б');
        hangmanGame.board.guess('у');
        GuessResult guessResult = hangmanGame.board.guess('з');

        assertEquals(Arrays.toString(hangmanGame.board.userAnswer), "[а, р, б, у, з]");
        assertEquals(hangmanGame.board.attempts, 0);
        assertEquals(guessResult, GuessResult.WIN);
    }

    @Test
    @DisplayName("Проигрыш")
    void defeatTest() {
        RandomSeed.random.setSeed(53);
        Player ConsolePlayer = new ConsolePlayer();
        HangmanGame hangmanGame = new HangmanGame(ConsolePlayer);
        hangmanGame.board.guess('й');
        hangmanGame.board.guess('ц');
        hangmanGame.board.guess('к');
        hangmanGame.board.guess('е');
        GuessResult guessResult = hangmanGame.board.guess('н');

        assertEquals(Arrays.toString(hangmanGame.board.userAnswer), "[*, *, *, *, *]");
        assertEquals(hangmanGame.board.attempts, 5);
        assertEquals(guessResult, GuessResult.DEFEAT);
    }

    @Test
    @DisplayName("Некорректная длина исходного слова")
    void incorrectLengthTest() {
        Throwable thrown = catchThrowable(() -> {
            RandomSeed.random.setSeed(5351);
            Player ConsolePlayer = new ConsolePlayer();
            HangmanGame hangmanGame = new HangmanGame(ConsolePlayer);
            hangmanGame.run();
        });
        assertThat(thrown).hasMessage("Некорректная длина загаданного слова");
    }
}
