package xyz.gdome.fakepaint.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Model {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FIELDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    ArrayList<Shape> toRender;
    private ShapeFactory shapeFactory;

    private ShapeType type;
    private Color color;
    private double radius;
    private double size;
    private double width;
    private double height;
    private double mouseX;
    private double mouseY;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~GETTER/SETTER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public double getMouseX() {
        return mouseX;
    }
    public double getMouseY() {
        return mouseY;
    }
    public ShapeType getType() {
        return this.type;
    }
    public Color getColor() {
        return this.color;
    }
    public double getRadius() {
        return this.radius;
    }
    public double getSize() {
        return this.size;
    }
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }

    public void setMouseX(double x) {
        this.mouseX = x;
    }
    public void setMouseY(double y) {
        this.mouseY = y;
    }
    public void setType(ShapeType type) {
        this.type = type;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Model() {

        toRender = new ArrayList<>();

    }








    public void createNewShape(Enum shapeType) {

        shapeFactory.createShape(shapeType, this.getColor(), this.getSize(), this.getWidth(), this.getHeight(), this.getRadius(), this.getMouseX(), this.getMouseY());

    }












    public void render(GraphicsContext gc) {
        if(!toRender.isEmpty()) {
            for(Shape shape:toRender) {
                shape.toDisplay(gc);
            }
        }
    }
    public void undo() {
        if(!toRender.isEmpty()) {
            toRender.remove(toRender.size()-1);
        }
    }
    public void clearCanvas(Canvas context) {
        context.getGraphicsContext2D().clearRect(0, 0, context.getWidth(), context.getHeight());
    }






    //private createShape(double mouseX, double mouseY, Color color, double radius) {}


    // Magisk klass som uppdateras så fort view uppdateras
    StringProperty message = new SimpleStringProperty();

    public ObservableList<String> getObservableList() {
        return observableList;
    }

    // En observerbar lista
    ObservableList<String> observableList = FXCollections.observableArrayList();

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }


    public void sendMessage() {
        getObservableList().add(getMessage());
        setMessage("");
    }


    //#####################################################################################################################







}
