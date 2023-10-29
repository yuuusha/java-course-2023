package edu.hw3.Task7;

import java.util.Comparator;

public class NullComparator<T extends Comparable<T>> implements Comparator<T> {

    @Override
    public int compare(T s1, T s2) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        } else {
            return s1.compareTo(s2);
        }
    }
}
