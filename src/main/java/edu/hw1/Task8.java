package edu.hw1;

public final class Task8 {

    private static final int[][] SHIFTS = {
        {-2, -1},
        {-2, 1},
        {-1, 2},
        {1, 2},
        {2, 1},
        {2, -1},
        {1, -2},
        {-1, -2}
    };

    private Task8() {

    }

    public static void checkIncorrectValue(int[][] board, int n) throws RuntimeException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 1 && board[i][j] != 0) {
                    throw new RuntimeException("Некорректный ввод: значение массива, отличное от 0 или 1");
                }
            }
        }
    }

    public static boolean knightBoardCapture(int[][] board) throws RuntimeException {

        final int n = 8;

        String lengthException = "Некорректный ввод: размерность массива не равна 8х8";
        if (board.length != n) {
            throw new RuntimeException(lengthException);
        } else {
            for (int i = 0; i < n; i++) {
                if (board[i].length != n) {
                    throw new RuntimeException(lengthException);
                }
            }
        }

        checkIncorrectValue(board, n);

        int shiftVer;
        int shiftHor;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    for (int[] shift : SHIFTS) {
                        shiftVer = i + shift[0];
                        shiftHor = j + shift[1];
                        if (shiftVer >= 0 && shiftVer < n && shiftHor >= 0 && shiftHor < n) {
                            if (board[shiftVer][shiftHor] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
