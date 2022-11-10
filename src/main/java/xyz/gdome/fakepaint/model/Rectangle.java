package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Shape{

    private Color color;
    private double centerX;
    private double centerY;
    private double width;
    private double height;



    public Rectangle(Color color, double width, double height, double x, double y) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.centerX = x;
        this.centerY = y;

    }

    public Shape returnShape() {
        return this;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }

    public void setSize(double size) {

    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        System.out.println(this + " should appear on the canvas");
        gc.setFill(this.color);
        gc.fillRect(this.centerX - width/2,this.centerY - height/2,this.width,this.height);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(this.centerX - width/2,this.centerY - height/2,this.width,this.height);
    }


    @Override
    public boolean isSelected(double x, double y) {
        System.out.println("isSelected is checking a rectangle");
        double westSide = centerX - (width/2);
        double lowerEdge = centerY - (height/2);
        double eastSide = centerX + (width/2);
        double upperEdge = centerY + (height/2);

        return (westSide <= x && x <= eastSide) && (lowerEdge <= y && y <= upperEdge);
    }
    @Override
    public String toSvg() {

        String color= "#"+this.getColor().toString().substring(2,10);

        return "<rect x=\"" + this.centerX + "\" y=\"" + this.centerY + "\" width=\"" + this.width + "\" height=\"" + this.height + "\" fill=\"" + color + "\"/>";
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "color=" + color +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
