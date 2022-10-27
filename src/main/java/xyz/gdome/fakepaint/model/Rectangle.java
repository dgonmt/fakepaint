package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{


    private double width;

    private double height;

    private double westSide = insertionCoordinateX - (width/2);
    private double lowerEdge = insertionCoordinateY - (height/2);
    private double eastSide = insertionCoordinateX + (width/2);
    private double upperEdge = insertionCoordinateY + (height/2);




    public Rectangle(Color color, double width, double height, double x, double y) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.insertionCoordinateX = x;
        this.insertionCoordinateY = y;

    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillRect(width,height,this.center.x(),this.center.y());
    }

    public void run() {



    }

    @Override
    public boolean isSelected(double x, double y) {
        if ((westSide <= x && x <= eastSide) && (lowerEdge <= y && x <= upperEdge)) {
            return true;
        }
        return false;
    }

}
