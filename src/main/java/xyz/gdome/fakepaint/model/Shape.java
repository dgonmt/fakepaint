package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements ShapeFactory{

    public Color color;

    public Position center;

    public double insertionCoordinateX;
    public double insertionCoordinateY;

    public Shape(Position center) {
        this.center = center;
    }

    public Shape createShape(Enum type, Color color, double size, double width, double height, double radius, double x, double y) {

        if (type == ShapeType.SQUARE) {
            return new Square(color, size, x, y);
        }
        if (type == ShapeType.RECTANGLE) {
            return new Rectangle(color, width, height, x, y);
        }
        if (type == ShapeType.CIRCLE) {
            return new Circle(color, radius, x, y);
        }
        return null;
    }

    public abstract void toDisplay(GraphicsContext gc);

    public abstract boolean isSelected(double x, double y);

}
