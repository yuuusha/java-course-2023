package edu.hw9;

import edu.hw9.Task3.Coordinate;
import edu.hw9.Task3.Maze;
import edu.hw9.Task3.MazeGenerator;
import edu.hw9.Task3.RandomSeed;
import edu.hw9.Task3.RecursiveBacktracking;
import edu.hw9.Task3.RecursiveBacktrackingPathMultiThread;
import edu.hw9.Task3.Renderer;
import edu.hw9.Task3.RendererConsole;
import edu.hw9.Task3.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Отрисовка пути RecursiveBacktrackingPathMultiThread")
    void printPathRecursiveBacktrackingTest() {
        RandomSeed.setSeed(5535);
        MazeGenerator mazeGenerator = new RecursiveBacktracking();
        Maze maze = mazeGenerator.generate(9, 17);
        Renderer renderer = new RendererConsole();

        String res = """
            █████████████████
            █.........█     █
            █████████.███ ███
            █   █   █...█   █
            █ ███ █ ███.███ █
            █     █   █.█...█
            █ ███████ █.█.█.█
            █       █  ...█.█
            █████████████████
            """;

        Solver findPath = new RecursiveBacktrackingPathMultiThread();
        List<Coordinate> path =
            findPath.solve(maze, new Coordinate(1, 1), new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2));

        assertEquals(res, renderer.render(maze, path));
    }

}
