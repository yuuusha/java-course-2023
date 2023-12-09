package edu.project4.baseObjects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {}

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        try {
            if (image == null || filename == null || format == null) {
                throw new IllegalArgumentException("Отсутствует обязательный параметр");
            }

            BufferedImage bufferedImage = toBufferedImage(image);
            ImageIO.write(bufferedImage, format.name(), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedImage toBufferedImage(FractalImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        Pixel pixel;
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                pixel = image.getPixel(x, y);
                if (pixel != null) {
                    bufferedImage.setRGB(x, y, pixel.getColor().getRGB());
                }
            }
        }
        return bufferedImage;
    }
}
