package edu.project4.render;

import edu.project4.baseObjects.AffineTransformation;
import edu.project4.baseObjects.Pixel;
import edu.project4.baseObjects.Point;
import java.awt.Color;

public abstract class AbstractRenderer implements Renderer {

    @SuppressWarnings("MagicNumber")
    protected Point pointTransform(Point p, AffineTransformation t) {
        // Перемножение матриц и сложение с вектор-столбцом
        double newX = t.coeff()[0] * p.x() + t.coeff()[1] * p.y() + t.coeff()[2];
        double newY = t.coeff()[3] * p.x() + t.coeff()[4] * p.y() + t.coeff()[5];
        return new Point(newX, newY);
    }

    protected void setColor(Pixel pixel, AffineTransformation affineTransformation) {
        if (pixel.getHitCount() == 0) {
            Color color = new Color(affineTransformation.color().getRed(), affineTransformation.color().getGreen(),
                affineTransformation.color().getBlue());
            pixel.setColor(color);
        } else {
            Color color = new Color((pixel.getColor().getRed() + affineTransformation.color().getRed()) / 2,
                (pixel.getColor().getGreen() + affineTransformation.color().getGreen()) / 2,
                (pixel.getColor().getBlue() + affineTransformation.color().getBlue()) / 2
            );
            pixel.setColor(color);
        }
    }

}
