package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {

    private Color color;
    private double centerX;
    private double centerY;
    private double size;


    public Square(Color color, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.centerX = x;
        this.centerY = y;
    }


    public Shape returnShape() {
        return this;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        System.out.println(this + " should appear on the canvas");
        gc.setFill(this.color);
        gc.fillRect(this.centerX - size / 2, this.centerY - size / 2, this.size, this.size);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(this.centerX - size / 2, this.centerY - size / 2, this.size, this.size);
    }


    @Override
    public boolean isSelected(double x, double y) {
        System.out.println("isSelected is checking a square");
        double westSide = this.centerX - (this.size / 2);
        double lowerEdge = this.centerY - (this.size / 2);
        double eastSide = this.centerX + (this.size / 2);
        double upperEdge = this.centerY + (this.size / 2);

        //System.out.println(westSide + " " + x + " " + eastSide);
        //System.out.println(lowerEdge + " " + y + " " + upperEdge);
        //System.out.println((westSide <= x && x <= eastSide) && (lowerEdge <= y && x <= upperEdge));

        return (westSide <= x && x <= eastSide) && (lowerEdge <= y && y <= upperEdge);
    }

    @Override
    public String toSvg() {
        return "<rect x=\"" + this.centerX + "\" y=\"" + this.centerY + "\" width=\"" + this.size + "\" height=\"" + this.size + "\" fill=\"" + this.color + "\"/>";
    }

    @Override
    public String toString() {
        return "Square{" +
                "color=" + color +
                ", insertionCoordinateX=" + centerX +
                ", insertionCoordinateY=" + centerY +
                ", size=" + size +
                '}';
    }

}
