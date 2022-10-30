package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {

    private Color color;
    private double insertionCoordinateX;
    private double insertionCoordinateY;
    private double size;




    private final double westSide = insertionCoordinateX - (size/2);
    private final double lowerEdge = insertionCoordinateY - (size/2);
    private final double eastSide = insertionCoordinateX + (size/2);
    private final double upperEdge = insertionCoordinateY + (size/2);

    public Square(Color color, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.insertionCoordinateX = x;
        this.insertionCoordinateY = y;

    }


    public Shape returnShape() {
        return this;
    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillRect(this.insertionCoordinateX,this.insertionCoordinateY,this.size,this.size);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(this.insertionCoordinateX,this.insertionCoordinateY,this.size,this.size);
    }


    @Override
    public boolean isSelected(double x, double y) {
        return (westSide <= x && x <= eastSide) && (lowerEdge <= y && x <= upperEdge);
    }
}
