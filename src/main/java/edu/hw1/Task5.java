package edu.hw1;

public final class Task5 {

    private Task5() {

    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(int inputNumber) throws RuntimeException {

        if (inputNumber < 0) {
            throw new RuntimeException("Некорректный ввод: число отрицательное");
        }

        int number = inputNumber;
        while (number > 9) {
            if (isPalindrome(number)) {
                return true;
            } else {
                number = createDescendant(number);
            }
        }
        return false;
    }

    private static boolean isPalindrome(int number) {
        String stringNumber = Integer.toString(number);
        for (int l = 0, r = stringNumber.length() - 1; l <= r; l++, r--) {
            if (stringNumber.charAt(l) != stringNumber.charAt(r)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("MagicNumber")
    private static int createDescendant(int number) {

        if (number < 10) {
            return number;
        }

        String stringNumber = Integer.toString(number);
        int fixLengthString = stringNumber.length() - stringNumber.length() % 2;

        int sum;
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < fixLengthString; i += 2) {
            sum = Character.getNumericValue(stringNumber.charAt(i))
                + Character.getNumericValue(stringNumber.charAt(i + 1));
            resultString.append(sum);
        }

        if (fixLengthString != stringNumber.length()) {
            resultString.append(stringNumber.charAt(stringNumber.length() - 1));
        }

        return Integer.parseInt(resultString.toString());
    }

}
