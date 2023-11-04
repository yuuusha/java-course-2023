package edu.project1;

import org.jetbrains.annotations.NotNull;

public final class Dictionary {

    public static final String[] DICT = {"яблоко", "банан", "арбуз", "помидор", "огурец", "ф"};

    private Dictionary() {

    }

    public static @NotNull String randomWord() {
        String word = DICT[RandomSeed.random.nextInt(DICT.length)];
        if (word.length() < 2) {
            throw new RuntimeException("Некорректная длина загаданного слова");
        }
        return word;
    }
}
