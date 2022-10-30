package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Shape {

    private Color color;
    private double insertionCoordinateX;
    private double insertionCoordinateY;
    private double radius;













    public Circle(Color color, double radius, double x, double y) {
        this.color = color;
        this.radius = radius;
        this.insertionCoordinateX = x;
        this.insertionCoordinateY = y;
    }



    public Shape returnShape() {
        return this;
    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillOval(this.insertionCoordinateX,this.insertionCoordinateY,this.radius,this.radius);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(this.insertionCoordinateX,this.insertionCoordinateY,this.radius,this.radius);
    }


    @Override
    public boolean isSelected(double x, double y) {
        return !(((x - this.insertionCoordinateX) + (y - this.insertionCoordinateY)) > (this.radius * this.radius));
    }

}
