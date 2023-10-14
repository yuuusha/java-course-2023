package edu.hw1;

public final class Task7 {

    private Task7() {

    }

    public static int rotateLeft(int n, int shift) throws RuntimeException {

        if (n <= 0) {
            throw new RuntimeException("Некорректный ввод: число неположительное");
        }

        if (shift < 0) {
            return rotateRight(n, Math.abs(shift));
        }

        String stringN = Integer.toBinaryString(n);
        int fixedShift = shift % stringN.length();

        return Integer.parseInt(stringN.substring(fixedShift) + stringN.substring(0, fixedShift), 2);
    }

    public static int rotateRight(int n, int shift) throws RuntimeException {

        if (shift < 0) {
            return rotateLeft(n, Math.abs(shift));
        }

        String stringN = Integer.toBinaryString(n);
        int fixedShift = shift % stringN.length();
        int shiftLeft = stringN.length() - fixedShift;

        return rotateLeft(n, shiftLeft);
    }
}
