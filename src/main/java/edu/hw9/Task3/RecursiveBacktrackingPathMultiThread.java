package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveBacktrackingPathMultiThread implements Solver {

    private final static int[] DH = {1, 0, -1, 0};
    private final static int[] DW = {0, 1, 0, -1};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {

        if (start.row() >= maze.getHeight() || start.col() >= maze.getWidth()
            || end.row() >= maze.getHeight() || end.col() >= maze.getWidth()
            || start.row() <= 0 || start.col() <= 0 || end.col() <= 0 || end.row() <= 0) {
            throw new RuntimeException("Точка за пределами границ лабиринта");
        }

        if (maze.getGrid()[start.row()][start.col()] == '█' || maze.getGrid()[end.row()][end.col()] == '█') {
            throw new RuntimeException("Попытка найти путь из/в непустую клетку");
        }

        List<Coordinate> coord = new ArrayList<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        boolean result = forkJoinPool.invoke(new FindPathTask(maze.getGrid(), start, end, coord));
        forkJoinPool.shutdown();

        if (result) {
            return coord;
        } else {
            throw new RuntimeException("Путь не найден");
        }
    }

    @SuppressWarnings("MagicNumber")
    private static class FindPathTask extends RecursiveTask<Boolean> {
        private final char[][] maze;
        private final Coordinate start;
        private final Coordinate end;
        private final List<Coordinate> coord;

        FindPathTask(char[][] maze, Coordinate start, Coordinate end, List<Coordinate> coord) {
            this.maze = maze;
            this.start = start;
            this.end = end;
            this.coord = coord;
        }

        @Override
        protected Boolean compute() {
            if (start.equals(end)) {
                coord.add(end);
                return true;
            }

            List<FindPathTask> tasks = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                int newH = start.row() + DH[i];
                int newW = start.col() + DW[i];

                if (newH > 0 && newW > 0 && newH < maze.length - 1 && newW < maze[0].length - 1
                    && maze[newH][newW] == ' ' && !coord.contains(new Coordinate(newH, newW))) {

                    List<Coordinate> newCoord = new ArrayList<>(coord);
                    newCoord.add(new Coordinate(start.row(), start.col()));

                    FindPathTask task = new FindPathTask(maze, new Coordinate(newH, newW), end, newCoord);
                    task.fork();
                    tasks.add(task);
                }
            }

            for (FindPathTask task : tasks) {
                if (task.join()) {
                    coord.addAll(task.coord);
                    return true;
                }
            }

            return false;
        }
    }
}

