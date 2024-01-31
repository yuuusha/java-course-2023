package edu.project4.render;

import edu.project4.baseObjects.AffineTransformation;
import edu.project4.baseObjects.FractalImage;
import edu.project4.baseObjects.Pixel;
import edu.project4.baseObjects.Point;
import edu.project4.baseObjects.Rect;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class SingleThreadRenderer extends AbstractRenderer {
    @Override
    @SuppressWarnings("MagicNumber")
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Function<Point, Point>> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        int transformIndex;
        Point point;
        Pixel pixel;
        int affineIndex;
        AffineTransformation[] setOfTransformations = AffineTransformation.getSetOfTransformations(samples);

        for (int sample = 0; sample < samples; sample++) {
            Point tmp = world.getRandPoint();
            for (int dot = -20; dot < iterPerSample; dot++) {
                affineIndex = ThreadLocalRandom.current().nextInt(0, samples);
                tmp = pointTransform(tmp, setOfTransformations[affineIndex]);

                if (dot < 0) {
                    continue;
                }
                transformIndex = ThreadLocalRandom.current().nextInt(0, variations.size());
                tmp = variations.get(transformIndex).apply(tmp);

                double rad = 0.0;
                for (int sectorOfSymmetry = 0; sectorOfSymmetry < symmetry; rad += 2 * Math.PI / symmetry,
                    sectorOfSymmetry++) {

                    point = Point.rotate(tmp, rad);

                    if (!world.contains(point)) {
                        continue;
                    }

                    pixel = canvas.getPixel(
                        (int) (((point.x() - world.x())) * canvas.width() / world.width()),
                        (int) ((point.y() - world.y()) * canvas.height() / world.height())
                    );
                    if (pixel != null) {
                        setColor(pixel, setOfTransformations[affineIndex]);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
        return canvas;
    }
}
