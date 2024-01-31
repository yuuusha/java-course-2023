package edu.project2;

import java.util.List;

public class RendererConsole implements Renderer {
    @Override
    public String render(Maze maze) {

        StringBuilder resultMaze = new StringBuilder();
        char[][] grid = maze.getGrid();

        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                resultMaze.append(grid[h][w]);
            }
            resultMaze.append('\n');
        }

        return resultMaze.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {

        StringBuilder resultMaze = new StringBuilder();
        char[][] grid = maze.getGrid();

        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                if (path.contains(new Coordinate(h, w))) {
                    resultMaze.append('.');
                } else {
                    resultMaze.append(grid[h][w]);
                }
            }
            resultMaze.append('\n');
        }

        return resultMaze.toString();
    }
}
