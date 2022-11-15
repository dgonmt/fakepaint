package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

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
    public Color getColor() {
        return this.color;
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

        String color= "#"+this.getColor().toString().substring(2,10);

        return "<circle cx=\"" + this.centerX + "\" cy=\"" + this.centerY + "\" r=\"" + this.radius / 2  + "\" fill=\"" + color + "\"/>";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle circle)) return false;
        return Double.compare(circle.centerX, centerX) == 0 && Double.compare(circle.centerY, centerY) == 0 && Double.compare(circle.radius, radius) == 0 && Objects.equals(color, circle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, centerX, centerY, radius);
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
