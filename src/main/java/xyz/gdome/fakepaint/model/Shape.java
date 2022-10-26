package xyz.gdome.fakepaint.model;

import javafx.scene.paint.Color;

public abstract class Shape {

    public Color color;

    public Position center;

    public abstract void draw();
    public abstract boolean isSelected(double x, double y);

    public Shape() {
        center = new Position(0,0);
    }
}
