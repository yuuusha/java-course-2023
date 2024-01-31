package edu.project4.processor;

import edu.project4.baseObjects.FractalImage;
import edu.project4.baseObjects.Pixel;
import java.awt.Color;

public class GammaProcessor implements ImageProcessor {

    private static final double GAMMA = 2.3;

    @Override
    public void process(FractalImage image) {
        double max = 0;
        Pixel pixel;
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                pixel = image.getPixel(x, y);
                if (pixel != null) {
                    if (pixel.getHitCount() != 0) {
                        pixel.setNormal(Math.log10(pixel.getHitCount()));
                        max = Math.max(max, pixel.getNormal());
                    }
                }
            }
        }

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                pixel = image.getPixel(x, y);
                if (pixel != null) {
                    pixel.setNormal(pixel.getNormal() / max);

                    Color color = new Color(
                        (int) (pixel.getColor().getRed() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))),
                        (int) (pixel.getColor().getGreen() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))),
                        (int) (pixel.getColor().getBlue() * Math.pow(pixel.getNormal(), (1.0 / GAMMA)))
                    );
                    pixel.setColor(color);
                }
            }
        }
    }
}
