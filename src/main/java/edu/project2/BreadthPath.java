package edu.project2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BreadthPath implements Solver {

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

        List<Coordinate> coord = findPath(maze.getGrid(), start, end);
        if (!coord.isEmpty()) {
            return coord;
        } else {
            throw new RuntimeException("Путь не найден");
        }
    }

    @SuppressWarnings("MagicNumber")
    private List<Coordinate> findPath(char[][] maze, Coordinate start, Coordinate end) {

        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(start);

        Map<Coordinate, Coordinate> parents = new HashMap<>();
        parents.put(start, null);

        while (!queue.isEmpty()) {
            Coordinate cur = queue.poll();

            if (cur.equals(end)) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newH = cur.row() + DH[i];
                int newW = cur.col() + DW[i];

                Coordinate newCoord = new Coordinate(newH, newW);
                if (newH > 0 && newW > 0 && newH < maze.length - 1 && newW < maze[0].length - 1
                    && maze[newH][newW] == ' ' && !parents.containsKey(newCoord)) {
                    parents.put(newCoord, cur);
                    queue.offer(newCoord);
                }
            }
        }

        LinkedList<Coordinate> coords = new LinkedList<>();
        Coordinate cur = new Coordinate(end.row(), end.col());

        while (cur != null) {
            coords.addFirst(cur);
            cur = parents.get(cur);
        }

        return coords;
    }
}
