package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements isDrawable, isSelectable{

    public Color color;

    public Position center;

    public abstract void draw(GraphicsContext gc, Color color, double radius);
    public abstract void draw(GraphicsContext gc, Color color, double width);
    public abstract void draw(GraphicsContext gc, double width, Color color);
    public abstract void draw(GraphicsContext gc, double width, Color color);
    public abstract boolean isSelected(double x, double y);

    public Shape() {
        center = new Position(0,0);
    }
}
