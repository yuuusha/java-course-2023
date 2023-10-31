package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    @DisplayName("null-ключ")
    void nullKeyTest() {
        TreeMap<String, String> tree = new TreeMap<>(Comparator.nullsFirst(
            Comparator.comparing(s -> s)
        ));
        tree.put(null, "test");
        assertTrue(tree.containsKey(null));
    }
}
