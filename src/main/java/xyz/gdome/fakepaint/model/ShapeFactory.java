package xyz.gdome.fakepaint.model;

import javafx.scene.paint.Color;

public class ShapeFactory {

    //Abstract factory pattern
    public Shape shapeBuilder(ShapeType shapeType, Color color, double size, double width, double height, double x, double y) {

        if (shapeType == ShapeType.SQUARE) {
            return new Square(color, size, x, y);
        }
        if (shapeType == ShapeType.RECTANGLE) {
            return new Rectangle(color, width, height, x, y);
        }
        if (shapeType == ShapeType.CIRCLE) {
            return new Circle(color, size, x, y);
        } else {
            return new DefaultShape();
        }
    }
}
