package edu.hw1;

public final class Task2 {

    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {

        int num = number;

        if (num < 0) {
            num = Math.abs(num);
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
