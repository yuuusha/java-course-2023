package edu.hw3;

import java.util.HashMap;
import java.util.List;

public final class Task3 {

    private Task3() {

    }

    public static <T> HashMap<T, Integer> freqDict(List<T> array) {

        HashMap<T, Integer> dict = new HashMap<>();

        for (T item: array) {
            dict.put(item, dict.getOrDefault(item, 0) + 1);
        }

        return dict;
    }
}
