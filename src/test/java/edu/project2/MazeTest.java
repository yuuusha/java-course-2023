package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeTest {

    @Test
    @DisplayName("Отрисовка лабиринта")
    void printMazeTest() {
        RandomSeed.setSeed(5535);
        MazeGenerator mazeGenerator = new RecursiveBacktracking();
        Maze maze = mazeGenerator.generate(9, 17);
        Renderer renderer = new RendererConsole();

        String res = """
            █████████████████
            █         █     █
            █████████ ███ ███
            █   █   █   █   █
            █ ███ █ ███ ███ █
            █     █   █ █   █
            █ ███████ █ █ █ █
            █       █     █ █
            █████████████████
            """;
        assertEquals(res, renderer.render(maze));
    }

    @Test
    @DisplayName("Отрисовка пути")
    void printPathTest() {
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

        Solver findPath = new RecursiveBacktrackingPath();
        List<Coordinate> path =
            findPath.solve(maze, new Coordinate(1, 1), new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2));

        assertEquals(res, renderer.render(maze, path));
    }

    @Test
    @DisplayName("Некорректно задан путь")
    void incorrectPathTest() {
        RandomSeed.setSeed(5535);
        MazeGenerator mazeGenerator = new RecursiveBacktracking();
        Maze maze = mazeGenerator.generate(9, 17);
        Solver findPath = new RecursiveBacktrackingPath();
        Throwable thrown = catchThrowable(() -> {
            findPath.solve(maze, new Coordinate(2, 1), new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2));
        });
        assertThat(thrown).hasMessage("Попытка найти путь из/в непустую клетку");
    }

    @Test
    @DisplayName("Некорректно задана точка")
    void incorrectCoordinateTest() {
        RandomSeed.setSeed(5535);
        MazeGenerator mazeGenerator = new RecursiveBacktracking();
        Maze maze = mazeGenerator.generate(9, 17);
        Solver findPath = new RecursiveBacktrackingPath();
        Throwable thrown = catchThrowable(() -> {
            findPath.solve(maze, new Coordinate(21, 21), new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2));
        });
        assertThat(thrown).hasMessage("Точка за пределами границ лабиринта");
    }
}
