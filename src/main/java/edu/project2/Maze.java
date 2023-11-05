package edu.project2;

public class Maze {
    private final int width;
    private final int height;
    private final char[][] grid;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getGrid() {
        return grid;
    }

    public Maze(int height, int width, char[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }
}
