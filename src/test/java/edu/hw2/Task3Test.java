package edu.hw2;

import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.RandomSeed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task3Test {

    @Test
    @DisplayName("Недостаточно попыток, FaultyConnectionManager")
    void notEnoughAttemptsFaultyConnectionManagerTest() {
        Throwable thrown = catchThrowable(() -> {
            RandomSeed.setSeed(5349);
            PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 2);
            executor.updatePackages();
        });
        assertThat(thrown).hasMessage("Соединение прервано, превышено количество попыток");
    }

    @Test
    @DisplayName("Достаточно попыток, FaultyConnectionManager")
    void enoughAttemptsFaultyConnectionManagerTest() {
        Throwable thrown = catchThrowable(() -> {
            RandomSeed.setSeed(5349);
            PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 3);
            executor.updatePackages();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Недостаточно попыток, DefaultConnectionManager")
    void notEnoughAttemptsDefaultConnectionManagerTest() {
        Throwable thrown = catchThrowable(() -> {
            RandomSeed.setSeed(5349);
            PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(), 1);
            executor.updatePackages();
        });
        assertThat(thrown).hasMessage("Соединение прервано, превышено количество попыток");
    }

    @Test
    @DisplayName("Достаточно попыток, DefaultConnectionManager")
    void enoughAttemptsDefaultConnectionManagerTest() {
        Throwable thrown = catchThrowable(() -> {
            RandomSeed.setSeed(5349);
            PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(), 3);
            executor.updatePackages();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

}
