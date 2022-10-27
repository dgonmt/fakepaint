package xyz.gdome.fakepaint.model;

import javafx.scene.paint.Color;

public interface ShapeFactory {

    public Shape createShape(Enum type, Color color, double size, double width, double height, double radius, double x, double y);

}
