package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Shape{

    private Color color;
    private double insertionCoordinateX;
    private double insertionCoordinateY;
    private double width;
    private double height;


    private final double westSide = insertionCoordinateX - (width/2);
    private final double lowerEdge = insertionCoordinateY - (height/2);
    private final double eastSide = insertionCoordinateX + (width/2);
    private final double upperEdge = insertionCoordinateY + (height/2);

    public Rectangle(Color color, double width, double height, double x, double y) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.insertionCoordinateX = x;
        this.insertionCoordinateY = y;

    }

    public Shape returnShape() {
        return this;
    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillRect(this.insertionCoordinateX,this.insertionCoordinateY,this.width,this.height);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(this.insertionCoordinateX,this.insertionCoordinateY,this.width,this.height);
    }


    @Override
    public boolean isSelected(double x, double y) {
        return (westSide <= x && x <= eastSide) && (lowerEdge <= y && x <= upperEdge);
    }

}
