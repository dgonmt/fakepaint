package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{

    private double size;

    private double westSide = this.center.x() - (size/2);
    private double lowerEdge = this.center.y() - (size/2);
    private double eastSide = this.center.x() + (size/2);
    private double upperEdge = this.center.x() + (size/2);




    @Override
    public void draw(GraphicsContext gc, Color color, double radius) {

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
