package xyz.gdome.fakepaint.model;

import javafx.scene.paint.Color;

public class ShapeFactory {

    public Shape shapeBuilder(ShapeType shapeType, Color color, double size, double width, double height, double radius, double x, double y) {

        if (shapeType == ShapeType.SQUARE) {
            return new Square(color, size, x, y);
        }
        if (shapeType == ShapeType.RECTANGLE) {
            return new Rectangle(color, width, height, x, y);
        }
        else {
            return new Circle(color, radius, x, y);
        }

//        switch (shapeType) {
//
//            case SQUARE ->  new Square(color, size, x, y);
//            case RECTANGLE -> new Rectangle(color, width, height, x, y);
//            case CIRCLE -> new Circle(color, radius, x, y);
//
//        }
//        return null;
    }

}
