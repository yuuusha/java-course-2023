package edu.hw1;

public final class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] array1, int[] array2) throws RuntimeException {

        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) {
            throw new RuntimeException("Некорректный ввод: пустой массив");
        }

        int array1MaxValue = array1[0];
        int array1MinValue = array1[0];

        for (int i : array1) {
            if (i > array1MaxValue) {
                array1MaxValue = i;
            }
            if (i < array1MinValue) {
                array1MinValue = i;
            }
        }

        int array2MaxValue = array2[0];
        int array2MinValue = array2[0];

        for (int i : array2) {
            if (i > array2MaxValue) {
                array2MaxValue = i;
            }
            if (i < array2MinValue) {
                array2MinValue = i;
            }
        }

        return (array1MinValue > array2MinValue && array1MaxValue < array2MaxValue);
    }
}
