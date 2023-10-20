package edu.hw2.Task2;


public class Square extends Rectangle {

    private int side;

    public Square(int side) {
        this.side = side;
    }

    public Square() {

    }

    @Override
    public Rectangle setWidth(int width) {
        return new Rectangle(width, side);
    }

    @Override
    public Rectangle setHeight(int height) {
        return new Rectangle(side, height);
    }

}
