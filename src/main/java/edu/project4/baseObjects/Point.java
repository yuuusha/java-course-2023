package edu.project4.baseObjects;

public record Point(double x, double y) {
    //для симметрии
    public static Point rotate(Point point, double rad) {
        double newX = point.x() * Math.cos(rad) - point.y() * Math.sin(rad);
        double newY = point.x() * Math.sin(rad) + point.y() * Math.cos(rad);
        return new Point(newX, newY);
    }
}
