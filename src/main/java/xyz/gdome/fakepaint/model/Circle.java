package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Shape {

    private Color color;
    private double centerX;
    private double centerY;
    private double radius;




    public Circle(Color color, double radius, double x, double y) {
        this.color = color;
        this.radius = radius;
        this.centerX = x;
        this.centerY = y;
    }



    public void setColor(Color color) {
        this.color = color;
    }
    public void setSize(double size) {
        this.radius = size;
    }
    public Shape returnShape() {
        return this;
    }

    @Override
    public void toDisplay(GraphicsContext gc) {
        System.out.println(this + " should appear on the canvas");
        gc.setFill(this.color);
        gc.fillOval(this.centerX - radius/2,this.centerY - radius/2,this.radius,this.radius);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(this.centerX - radius/2,this.centerY - radius/2,this.radius,this.radius);
    }


    @Override
    public boolean isSelected(double x, double y) {
        System.out.println("isSelected is checking a circle");

        return (Math.sqrt((((x - this.centerX) * (x - this.centerX)) + ((y - this.centerY) * (y - this.centerY)))) <= (this.radius / 2));
    }
    @Override
    public String toSvg() {
        return "<circle cx=\"" + this.centerX + "\" cy=\"" + this.centerY + "\" r=\"" + this.radius + "\" fill=\"" + this.color + "\"/>";
    }

    @Override
    public String toString() {
        return "Circle{" +
                "color=" + color +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", radius=" + radius +
                '}';
    }
}
