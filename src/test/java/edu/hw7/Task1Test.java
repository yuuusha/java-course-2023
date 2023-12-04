package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Многопоточный инкремент")
    void atomicIncrementerTest() {
        assertEquals(Task1.incrementer(), 100000);
    }

}
