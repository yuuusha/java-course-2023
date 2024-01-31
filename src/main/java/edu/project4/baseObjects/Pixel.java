package edu.project4.baseObjects;

import java.awt.Color;

public class Pixel {

    private Color color;
    private int hitCount;
    private double normal;

    public Pixel(Color color, int hitCount, double normal) {
        this.color = color;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public Color getColor() {
        return color;
    }

    public int getHitCount() {
        return hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
