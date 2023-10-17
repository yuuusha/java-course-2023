package edu.hw1;

public final class Task2 {

    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {

        int num = Math.abs(number);

        if (num == Integer.MIN_VALUE) {
            return 1;
        }

        if (num == 0) {
            return 1;
        }

        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }

        return count;
    }

}
