package edu.project2;

import java.util.List;

public final class Main {

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {

        MazeGenerator mazeGenerator = new RecursiveBacktracking();
        Maze maze = mazeGenerator.generate(21, 43);
        Renderer renderer = new RendererConsole();
        System.out.println(renderer.render(maze));

        Solver findPath = new RecursiveBacktrackingPath();
        List<Coordinate> path =
            findPath.solve(maze, new Coordinate(1, 1), new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2));

        System.out.println(renderer.render(maze, path));

    }
}
