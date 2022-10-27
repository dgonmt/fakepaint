package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    private double size;

    private double westSide = insertionCoordinateX - (size/2);
    private double lowerEdge = insertionCoordinateY - (size/2);
    private double eastSide = insertionCoordinateX + (size/2);
    private double upperEdge = insertionCoordinateY + (size/2);



    public Square(Color color, double size, double x, double y) {
        this.size = size;
        this.color = color;
        this.insertionCoordinateX = x;
        this.insertionCoordinateY = y;
    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        gc.fillRect(size,size,this.center.x(),this.center.y());
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
