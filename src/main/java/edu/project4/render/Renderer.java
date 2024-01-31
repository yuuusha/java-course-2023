package edu.project4.render;

import edu.project4.baseObjects.FractalImage;
import edu.project4.baseObjects.Point;
import edu.project4.baseObjects.Rect;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Function<Point, Point>> variations,
        int samples,
        int iterPerSample,
        int symmetry
    );
}
