package edu.project2;

import java.util.Random;

public class RandomSeed {
    public static final Random RANDOM = new Random();

    private RandomSeed() {

    }

    public static void setSeed(int seed) {
        RANDOM.setSeed(seed);
    }
}
