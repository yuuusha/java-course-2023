package edu.hw1;

import java.util.Arrays;

public final class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] array1, int[] array2)  {

        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) {
            throw new RuntimeException("Некорректный ввод: пустой массив");
        }

        int array1MaxValue = Arrays.stream(array1).max().getAsInt();
        int array1MinValue = Arrays.stream(array1).min().getAsInt();

        for (int i : array1) {
            if (i > array1MaxValue) {
                array1MaxValue = i;
            }
            if (i < array1MinValue) {
                array1MinValue = i;
            }
        }

        int array2MaxValue = Arrays.stream(array2).max().getAsInt();
        int array2MinValue = Arrays.stream(array2).min().getAsInt();

        for (int i : array2) {
            if (i > array2MaxValue) {
                array2MaxValue = i;
            }
            if (i < array2MinValue) {
                array2MinValue = i;
            }
        }

        return array1MinValue > array2MinValue && array1MaxValue < array2MaxValue;
    }
}
