package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class RecursiveBacktrackingPath implements Solver {

    private final static int[] DH = {1, 0, -1, 0};
    private final static int[] DW = {0, 1, 0, -1};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {

        if (start.row() >= maze.getHeight() || start.col() >= maze.getWidth()
            || end.row() >= maze.getHeight() || end.col() >= maze.getWidth()
            || start.row() <= 0 || start.col() <= 0 || end.col() <= 0 || end.row() <= 0) {
            throw new RuntimeException("Точка за пределами границ лабиринта");
        }

        if (maze.getGrid()[start.row()][start.col()] == '█' || maze.getGrid()[end.row()][end.col()]  == '█') {
            throw new RuntimeException("Попытка найти путь из/в непустую клетку");
        }

        List<Coordinate> coord = new ArrayList<>();
        if (findPath(maze.getGrid(), start, end, coord)) {
            return coord;
        } else {
            throw new RuntimeException();
        }

    }

    @SuppressWarnings("MagicNumber")
    private boolean findPath(char[][] maze, Coordinate start, Coordinate end, List<Coordinate> coord) {

        if (start.equals(end)) {
            coord.add(end);
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newH = start.row() + DH[i];
            int newW = start.col() + DW[i];

            if (newH > 0 && newW > 0 && newH < maze.length - 1 && newW < maze[0].length - 1 && maze[newH][newW] == ' '
                && !coord.contains(new Coordinate(newH, newW))) {
                coord.add(new Coordinate(start.row(), start.col()));
                if (findPath(maze, new Coordinate(newH, newW), end, coord)) {
                    return true;
                }
                coord.remove(new Coordinate(start.row(), start.col()));
            }
        }

        return false;
    }
}
