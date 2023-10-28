package edu.hw2.Task3;

import java.util.Random;

public class RandomSeed {
    public static final Random RANDOM = new Random();

    private RandomSeed() {

    }

    public static boolean isAttemptOK(int prob) {
        return RANDOM.nextInt(prob) == 0;
    }

    public static void setSeed(int seed) {
        RANDOM.setSeed(seed);
    }
}
