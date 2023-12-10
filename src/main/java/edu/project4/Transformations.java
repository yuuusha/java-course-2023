package edu.project4;

import edu.project4.baseObjects.Point;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class Transformations {

    private Transformations() {

    }

    public static Point sinus(Point point) {
        return new Point(
            sin(point.x()),
            sin(point.y())
        );
    }

    public static Point cosin(Point point) {
        return new Point(
            cos(point.x()),
            cos(point.y())
        );
    }

    public static Point disk(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y) * PI;
        double theta = atan2(y, x) / PI;
        double newX = theta * sin(r);
        double newY = theta * cos(r);
        return new Point(newX, newY);
    }

    @SuppressWarnings("MagicNumber")
    public static Point bubble(Point point) {
        double x = point.x();
        double y = point.y();
        double r = 4 + x * x + y * y;
        double newX = (4.0 * x) / r;
        double newY = (4.0 * y) / r;
        return new Point(newX, newY);
    }

    public static Point cylinder(Point point) {
        return new Point(sin(point.x()), point.y());
    }

    public static Point diamond(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);
        double theta = atan2(y, x);
        double newX = sin(theta) * cos(r);
        double newY = cos(theta) * sin(r);
        return new Point(newX, newY);
    }

    public static Point exponential(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = exp(x - 1.0) * cos(PI * y);
        double newY = exp(x - 1.0) * sin(PI * y);
        return new Point(newX, newY);
    }

    public static Point fisheye(Point point) {
        double x = point.x();
        double y = point.y();
        double r = 2.0 / (1.0 + sqrt(x * x + y * y));
        double newX = r * y;
        double newY = r * x;
        return new Point(newX, newY);
    }

    public static Point heart(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);
        double theta = atan2(y, x);
        return new Point(
            r * sin(theta * r),
            -r * cos(theta * r)
        );
    }

    public static Point linear(Point point) {
        return new Point(
            point.x(),
            point.y()
        );
    }

    public static Point polar(Point point) {
        double x = point.x();
        double y = point.y();
        return new Point(
            atan2(y, x) / Math.PI,
            sqrt(x * x + y * y) - 1
        );
    }

    public static Point hyperbolic(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);
        double theta = atan2(y, x);
        return new Point(
            sin(theta) / r,
            r * cos(theta)
        );
    }

    public static Point spherical(Point point) {
        double x = point.x();
        double y = point.y();
        double r = 1 / (x * x + y * y);
        return new Point(
            r * x,
            r * y
        );
    }

    public static Point spiral(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);
        double theta = atan2(y, x);
        double newX = (1.0 / r) * (cos(theta) + sin(r));
        double newY = (1.0 / r) * (sin(theta) - cos(r));
        return new Point(newX, newY);
    }

    public static Point swirl(Point point) {
        double x = point.x();
        double y = point.y();
        double r = x * x + y * y;
        return new Point(
            x * sin(r) - y * cos(r),
            x * cos(r) + y * sin(r)
        );
    }

    public static Point waves(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = x + sin(y);
        double newY = y + sin(x);
        return new Point(newX, newY);
    }
}
