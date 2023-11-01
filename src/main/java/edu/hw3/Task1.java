package edu.hw3;

import java.util.HashMap;

public final class Task1 {

    public static HashMap<Character, Character> alphabet;

    public static void setAlphabet() {
        alphabet = new HashMap<>();
        for (int i = 'a'; i <= 'z'; i++) {
            alphabet.put((char) i, (char) ('z' - (i - 'a')));
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            alphabet.put((char) i, (char) ('Z' - (i - 'A')));
        }
    }

    private Task1() {

    }

    public static String atbash(String inputString) {
        setAlphabet();
        StringBuilder resultString = new StringBuilder();
        for (char ch: inputString.toCharArray()) {
            if (Character.isAlphabetic(ch)
                    && Character.UnicodeBlock.of(ch).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                resultString.append(alphabet.get(ch));
            } else {
                resultString.append(ch);
            }
        }
        return resultString.toString();
    }

}
