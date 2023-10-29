package edu.hw3;

import edu.hw3.Task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {

    @Test
    @DisplayName("null-ключ")
    void nullKeyTest() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator());
        tree.put(null, "test");
        assertTrue(tree.containsKey(null));
    }
}
