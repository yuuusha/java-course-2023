package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    @DisplayName("Наибольший элемент очереди")
    void greaterStockTest() {
        StockQueue q = new StockQueue();
        q.add(new Stock("A", 10));
        q.add(new Stock("B", 100));
        q.add(new Stock("C", 200));
        q.add(new Stock("D", 5));

        assertEquals(new Stock("C", 200), q.mostValuableStock());
    }

    @Test
    @DisplayName("Добавление существующего элемента в очередь")
    void alreadyExistTest() {
        StockQueue q = new StockQueue();
        q.add(new Stock("A", 10));
        q.add(new Stock("B", 100));
        Throwable thrown = catchThrowable(() -> {
            q.add(new Stock("B", 100));
        });
        assertThat(thrown).hasMessage("Акция уже существует");
    }
    @Test
    @DisplayName("Удаление несуществующего элемента в очереди")
    void notExistTest() {
        StockQueue q = new StockQueue();
        q.add(new Stock("A", 10));
        q.add(new Stock("B", 100));
        Throwable thrown = catchThrowable(() -> {
            q.remove(new Stock("C", 200));
        });
        assertThat(thrown).hasMessage("Такой акции нет");
    }
}
