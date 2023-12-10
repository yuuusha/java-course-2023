package edu.project4;

import edu.project4.baseObjects.ImageFormat;
import edu.project4.baseObjects.Point;
import edu.project4.baseObjects.Rect;
import edu.project4.processor.GammaProcessor;
import edu.project4.render.MultiThreadRenderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlameGeneratorTest {

    @Test
    @DisplayName("Создание файла")
    void fileCreateTest() {
        Path path = Path.of("src/main/resources/project4/file.png");
        List<Function<Point, Point>> transformations = new ArrayList<>();
        transformations.add(Transformations::linear);

        FlameGenerator generator =
            new FlameGenerator(
                1920,
                1080,
                new MultiThreadRenderer(4),
                new Rect(-1, -1, 2, 2),
                transformations,
                new GammaProcessor()
            );

        generator.generateFractalFlame(
            4,
            100000,
            1,
            path,
            ImageFormat.PNG
        );

        assertTrue(Files.exists(path));
    }

}
