package edu.project4;

import edu.project4.baseObjects.ImageFormat;
import edu.project4.baseObjects.Point;
import edu.project4.baseObjects.Rect;
import edu.project4.processor.GammaProcessor;
import edu.project4.render.MultiThreadRenderer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Main {

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {

        Path path = Path.of("src/main/resources/project4/file.png");
        List<Function<Point, Point>> transformations = new ArrayList<>();
        //transformations.add(Transformations::sinus);
        //transformations.add(Transformations::cosin);
        //transformations.add(Transformations::disk);
        //transformations.add(Transformations::bubble);
        //transformations.add(Transformations::cylinder);
        //transformations.add(Transformations::diamond);
        //transformations.add(Transformations::exponential);
        //transformations.add(Transformations::fisheye);
        //transformations.add(Transformations::heart);
        transformations.add(Transformations::linear);
        //transformations.add(Transformations::polar);
        transformations.add(Transformations::hyperbolic);
        //transformations.add(Transformations::spherical);
        //transformations.add(Transformations::spiral);
        //transformations.add(Transformations::swirl);
        //transformations.add(Transformations::waves);



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
            5000000,
            1,
            path,
            ImageFormat.PNG
        );

    }
}
