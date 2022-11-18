package xyz.gdome.fakepaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public interface Shape {

    public void toDisplay(GraphicsContext gc);
    public boolean isSelected(double x, double y);

    public void setColor(Color color);

    public void setSize(double size);

    public String toSvg();




}
