package edu.project4.baseObjects;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return p.x() <= x + width && p.x() >= x && p.y() <= y + height && p.y() >= y;
    }

    public Point getRandPoint() {
        double newX = width * ThreadLocalRandom.current().nextDouble();
        double newY = height * ThreadLocalRandom.current().nextDouble();
        return new Point(newX, newY);
    }
}
