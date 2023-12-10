package edu.project4.render;

import edu.project4.baseObjects.AffineTransformation;
import edu.project4.baseObjects.FractalImage;
import edu.project4.baseObjects.Pixel;
import edu.project4.baseObjects.Point;
import edu.project4.baseObjects.Rect;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class MultiThreadRenderer extends AbstractRenderer {

    private final int countOfThreads;

    public MultiThreadRenderer(int count) {
        this.countOfThreads = count;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Function<Point, Point>> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        AffineTransformation[] setOfTransformations = AffineTransformation.getSetOfTransformations(samples);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int sample = 0; sample < samples; sample++) {
            tasks.add(() -> {
                taskRender(
                    canvas,
                    world,
                    variations,
                    samples,
                    iterPerSample,
                    symmetry,
                    setOfTransformations
                );
                return null;
            });

        }
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        return canvas;
    }

    @SuppressWarnings("MagicNumber")
    private void taskRender(
        FractalImage canvas,
        Rect world,
        List<Function<Point, Point>> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        AffineTransformation[] transformations
    ) {
        int variationIndex;
        Point point;
        Pixel pixel;
        int indexTransformation;

        Point tmp = world.getRandPoint();
        for (int dot = -20; dot < iterPerSample; dot++) {
            indexTransformation = ThreadLocalRandom.current().nextInt(0, samples);
            tmp = pointTransform(tmp, transformations[indexTransformation]);

            if (dot < 0) {
                continue;
            }
            variationIndex = ThreadLocalRandom.current().nextInt(0, variations.size());
            tmp = variations.get(variationIndex).apply(tmp);

            double theta = 0.0;
            for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {
                point = Point.rotate(tmp, theta);

                if (!world.contains(point)) {
                    continue;
                }

                pixel = canvas.getPixel(
                    (int) (((point.x() - world.x())) * canvas.width() / world.width()),
                    (int) ((point.y() - world.y()) * canvas.height() / world.height())
                );
                if (pixel != null) {
                    synchronized (pixel) {
                        setColor(pixel, transformations[indexTransformation]);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
    }
}
