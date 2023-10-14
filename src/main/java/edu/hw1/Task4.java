package edu.hw1;

public final class Task4 {

    private Task4() {

    }

    public static String fixString(String inputString) {

        char[] inputChars = inputString.toCharArray();

        //Уменьшаем длину строки на 1 в случае, если количество символов нечетно
        int fixCharArrayLength = inputChars.length - inputChars.length % 2;

        for (int i = 0; i < fixCharArrayLength; i += 2) {
            swap(inputChars, i, i + 1);
        }

        return new String(inputChars);
    }

    private static void swap(char[] charArray, int index1, int index2) {
        char tmp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = tmp;
    }

}
