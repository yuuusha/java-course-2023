package edu.project4.baseObjects;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineTransformation(double[] coeff, Color color) {
    public static AffineTransformation[] getSetOfTransformations(int count) {
        AffineTransformation[] newTransformations = new AffineTransformation[count];
        for (int i = 0; i < count; i++) {
            newTransformations[i] = AffineTransformation.generate();
        }
        return newTransformations;
    }

    @SuppressWarnings("MagicNumber")
    public static AffineTransformation generate() {
        double[] newCoeff = new double[6];
        do {
            for (int i = 0; i < 6; i++) {
                newCoeff[i] = ThreadLocalRandom.current().nextDouble(-1.5, 1.5);
            }
        } while ((newCoeff[0] * newCoeff[0] + newCoeff[3] * newCoeff[3]) < 1
            && (newCoeff[1] * newCoeff[1] + newCoeff[4] * newCoeff[4]) < 1
            && (newCoeff[0] * newCoeff[0] + newCoeff[1] * newCoeff[1]
            + newCoeff[3] * newCoeff[3] + newCoeff[4] * newCoeff[4]) < 1
            + (newCoeff[0] * newCoeff[4] - newCoeff[1] * newCoeff[3])
            * (newCoeff[0] * newCoeff[4] - newCoeff[1] * newCoeff[3]));
        return new AffineTransformation(newCoeff, getRandColor());
    }

    @SuppressWarnings("MagicNumber")
    private static Color getRandColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, 256),
            ThreadLocalRandom.current().nextInt(0, 256),
            ThreadLocalRandom.current().nextInt(0, 256)
        );
    }
}
