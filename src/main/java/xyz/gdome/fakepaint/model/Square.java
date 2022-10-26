package xyz.gdome.fakepaint.model;

public class Square extends Shape{

    private double size;

    private double westSide = this.center.x() - (size/2);
    private double southSide = this.center.y() - (size/2);
    private double eastSide = this.center.x() + (size/2);
    private double northSide = this.center.x() + (size/2);



    @Override
    public void draw() {

    }

    @Override
    public boolean isSelected(double x, double y) {
        if ((westSide <= x && x <= eastSide) && (southSide <= y && x <= northSide)) {
            return true;
        }
        return false;
    }
}
