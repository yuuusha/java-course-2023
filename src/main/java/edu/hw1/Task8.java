package edu.hw1;

public final class Task8 {
    private static final int BOARD_SIZE = 8;
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

    public static boolean checkIncorrectValue(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != 1 && board[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean knightBoardCapture(int[][] board) {

        String lengthException = "Некорректный ввод: размерность массива не равна 8х8";
        if (board.length != BOARD_SIZE) {
            throw new RuntimeException(lengthException);
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i].length != BOARD_SIZE) {
                throw new RuntimeException(lengthException);
            }
        }

        if (!checkIncorrectValue(board)) {
            throw new RuntimeException("Некорректный ввод: значение массива, отличное от 0 или 1");
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 1) {
                    for (int[] shift : SHIFTS) {
                        int shiftVer = i + shift[0];
                        int shiftHor = j + shift[1];
                        if (shiftVer >= 0 && shiftVer < BOARD_SIZE && shiftHor >= 0 && shiftHor < BOARD_SIZE) {
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
