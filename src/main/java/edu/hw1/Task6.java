package edu.hw1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Task6 {

    private Task6() {

    }

    @SuppressWarnings("MagicNumber")
    public static int stepsOfK(int number)  {

        if (number > 9999 || number < 1000) {
            throw new RuntimeException("Некорректный ввод: число не является четырехзначным");
        }

        if (isUniqDigit(number)) {
            throw new RuntimeException("Некорректный ввод: число не должно состоять из одинаковых цифр");
        }

        int workNumber = number;
        int steps = 0;
        while (workNumber != 6174) {

            //"Сохранение" нулей
            while (workNumber < 1000) {
                workNumber *= 10;
            }

            workNumber = kaprekar(workNumber);
            steps++;
        }

        return steps;
    }

    private static int kaprekar(int number) {
        String numberAsc = Integer.toString(number);
        String numberDec;

        char[] numberChars = numberAsc.toCharArray();
        Arrays.sort(numberChars);
        numberAsc = new String(numberChars);
        numberDec = new StringBuilder(new String(numberChars)).reverse().toString();

        return Integer.parseInt(numberDec) - Integer.parseInt(numberAsc);
    }

    @SuppressWarnings("MagicNumber")
    private static boolean isUniqDigit(int number) {
        Set<Integer> digits = new HashSet<>();

        int workNumber = number;
        int tmp;

        while (workNumber > 0) {
            tmp = workNumber % 10;
            digits.add(tmp);
            workNumber /= 10;
        }

        return digits.size() == 1;
    }
}
