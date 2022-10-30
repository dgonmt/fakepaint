package xyz.gdome.fakepaint.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Model {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FIELDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    ArrayList<Shape> toRender;
    private Shape selectedShape;
    private ShapeFactory shapeFactory;
    private ShapeType type;
    private Color color;
    private double radius;
    private double size;
    private String dimension;
    private double width;
    private double height;
    private double mouseX;
    private double mouseY;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE FIELDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private SimpleListProperty<Shape> observableToRender;
    private SimpleStringProperty observableShapeType;
    private SimpleDoubleProperty observableRadius;
    private SimpleDoubleProperty observableSize;
    private SimpleStringProperty observableDimension;
    private SimpleDoubleProperty observableWidth;
    private SimpleDoubleProperty observableHeight;
    private SimpleDoubleProperty observableMouseX;
    private SimpleDoubleProperty observableMouseY;


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
    public String getDimension() {
        return this.dimension;
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
    //TODO Rewrite this method to parse a string and return a dimension which will be a list of size 2 , if list[0] == null then something is worng, if list[1] == null then it is either a size or a radius.
    public void setSize(double size) {
        this.size = size;
    }
    public void setDimension(String dimension) {
        this.dimension =dimension;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE GETTERS/SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public String getObservableShapeType() {
        return observableShapeType.getValue();
    }
    public double getObservableRadius() {
        return observableRadius.getValue();
    }
    public double getObservableSize() {
        return observableSize.getValue();
    }
    public String getObservableDimension() {
        return observableDimension.getValue();
    }
    public double getObservableWidth() {
        return observableWidth.getValue();
    }
    public double getObservableHeight() {
        return observableHeight.getValue();
    }
    public double getObservableMouseX() {
        return observableMouseX.getValue();
    }
    public double getObservableMouseY() {
        return observableMouseY.getValue();
    }

    public void setObservableShapeType(String shapeType) {
        this.observableShapeType.set(shapeType);
    }
    public void setObservableRadius(double radius) {
        this.observableRadius.set(radius);
    }
    public void setObservableSize(double size) {
        this.observableSize.set(size);
    }
    public void setObservableDimension(String dimenion) {
        this.observableDimension.set(dimension);
    }
    public void setObservableWidth(double width) {
        this.observableWidth.set(width);
    }
    public void setObservableHeight(double height) {
        this.observableHeight.set(height);
    }
    public void setObservableMouseX(double x) {
        this.observableMouseX.set(x);
    }
    public void setObservableMouseY(double y) {
        this.observableMouseY.set(y);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE PROPERTIES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public DoubleProperty radiusProperty() {
        return observableRadius;
    }
    public DoubleProperty sizeProperty() {
        return observableSize;
    }
    public StringProperty dimensionProperty() {
        return observableDimension;
    }
    public DoubleProperty widthProperty() {
        return observableWidth;
    }
    public DoubleProperty heightProperty() {
        return observableHeight;
    }
    public DoubleProperty mouseXProperty() {
        return observableMouseX;
    }
    public DoubleProperty mouseYProperty() {
        return observableMouseY;
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Model() {

        toRender = new ArrayList<>();
        shapeFactory = new ShapeFactory();


        this.setWidth(120);
        this.setHeight(120);
        this.setColor(Color.FIREBRICK);
        this.setMouseX(0);
        this.setMouseY(0);
        toRender.add(newShape(ShapeType.RECTANGLE));

        observableToRender = new SimpleListProperty<>();
        observableRadius = new SimpleDoubleProperty();
        observableSize = new SimpleDoubleProperty();
        observableDimension = new SimpleStringProperty();
        observableWidth = new SimpleDoubleProperty();
        observableHeight = new SimpleDoubleProperty();
        observableMouseX = new SimpleDoubleProperty();
        observableMouseY = new SimpleDoubleProperty();


    }

    public Shape newShape(ShapeType shapeType) {

         return shapeFactory.shapeBuilder(shapeType, getColor(), getSize(), getWidth(), getHeight(), getRadius(), getMouseX(), getMouseY());


    }
    private Shape findLatestCreatedShape(ArrayList<Shape> objectsPointedAt) {
        if (objectsPointedAt.size() == 1) {
            return objectsPointedAt.get(0);
        } else {
            return objectsPointedAt.get(objectsPointedAt.size()-1);
        }
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
