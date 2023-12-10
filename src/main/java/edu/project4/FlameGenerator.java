package edu.project4;

import edu.project4.baseObjects.FractalImage;
import edu.project4.baseObjects.ImageFormat;
import edu.project4.baseObjects.ImageUtils;
import edu.project4.baseObjects.Point;
import edu.project4.baseObjects.Rect;
import edu.project4.processor.ImageProcessor;
import edu.project4.render.Renderer;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class FlameGenerator {
    private final Renderer renderer;
    private final FractalImage canvas;
    private final Rect world;
    private final List<Function<Point, Point>> variations;
    private final ImageProcessor imageProcessor;

    public FlameGenerator(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Function<Point, Point>> variations,
        ImageProcessor imageProcessor
    ) {
        this.canvas = FractalImage.create(width, height);
        this.renderer = renderer;
        this.world = world;
        this.variations = variations;
        this.imageProcessor = imageProcessor;
    }

    public void generateFractalFlame(
        int samples,
        int iterPerSample,
        int symmetry,
        Path filename,
        ImageFormat imageFormat
    ) {
        if (renderer == null || world == null || variations == null || variations.isEmpty() || imageProcessor == null) {
            throw new IllegalArgumentException("Отсутствует обязательный параметр");
        }
        renderer.render(canvas, world, variations, samples, iterPerSample, symmetry);
        imageProcessor.process(canvas);
        ImageUtils.save(canvas, filename, imageFormat);
    }
}
