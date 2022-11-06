package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DefaultShape implements Shape{

    @Override
    public void toDisplay(GraphicsContext gc) {

    }

    @Override
    public boolean isSelected(double x, double y) {
        return false;
    }

    @Override
    public Shape returnShape() {
        return null;
    }

    @Override
    public String toSvg() {
        return "";
    }

    public void setColor(Color color) {

    }

    public void setSize(double size) {

    }

    @Override
    public String toString() {
        return "DefaultShape{EMPTY}";
    }
}
