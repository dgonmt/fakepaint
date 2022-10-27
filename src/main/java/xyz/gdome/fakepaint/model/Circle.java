package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{

    private double radius;
    private Color color;



    public Circle() {

    }
    public Circle(Color color, double radius, double x, double y) {
        this.color = color;
        this.radius = radius;
        this.insertionCoordinateX = x;
        this.insertionCoordinateY = y;
    }


    @Override
    public void toDisplay(GraphicsContext gc) {

    }

    public void run() {



    }

    @Override
    public boolean isSelected(double x, double y) {
        return !(((x - this.center.x()) + (y - this.center.y())) > (radius * radius));
    }

}
