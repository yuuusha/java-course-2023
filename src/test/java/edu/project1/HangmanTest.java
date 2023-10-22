package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanTest {
    @Test
    @DisplayName("Успешное угадывание")
    void successfulGuessTest() {
        Session session = new Session("банан", new char[] {'*', '*', 'н', '*', 'н'}, 5, 1);
        GuessResult guessResult = session.guess('б');
        assertThat(guessResult).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(guessResult.state()).containsExactly('б', '*', 'н', '*', 'н');
        assertEquals(1, guessResult.attempt());
    }

    @Test
    @DisplayName("Неудачное угадывание")
    void failedGuessTest() {
        Session session = new Session("банан", new char[] {'*', '*', 'н', '*', 'н'}, 5, 1);
        GuessResult guessResult = session.guess('в');
        assertThat(guessResult).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(guessResult.state()).containsExactly('*', '*', 'н', '*', 'н');
        assertEquals(2, guessResult.attempt());
    }

    @Test
    @DisplayName("Победа")
    void winTest() {
        Session session = new Session("банан", new char[] {'*', 'а', 'н', 'а', 'н'}, 5, 1);
        GuessResult guessResult = session.guess('б');
        assertThat(guessResult).isInstanceOf(GuessResult.Win.class);
    }

    @Test
    @DisplayName("Проигрыш")
    void defeatTest() {
        Session session = new Session("банан", new char[] {'*', 'а', 'н', 'а', 'н'}, 5, 4);
        GuessResult guessResult = session.guess('в');
        assertThat(guessResult).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    @DisplayName("Некорректная длина исходного слова")
    void incorrectLengthTest() {
        Throwable thrown = catchThrowable(() -> {
            RandomSeed.random.setSeed(5351);
            ConsoleHangman.run();
        });
        assertThat(thrown).hasMessage("Некорректная длина загаданного слова");
    }
}
