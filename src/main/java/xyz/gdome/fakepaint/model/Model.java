package xyz.gdome.fakepaint.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Model {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FIELDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private List<Shape> toRender;
    private List<Shape> toStore;
    private Shape highlightedShape;
    public int indexOfHighlightedShape;
    private final ShapeFactory shapeFactory;
    private ShapeType type;
    //private Color color;
    private double size;
    private double width;
    private double height;
    private double mouseX;
    private double mouseY;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE FIELDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private SimpleListProperty<Shape> observableToRender;
    private ObservableList<Shape> observableList = FXCollections.observableArrayList();
    private SimpleObjectProperty<ShapeType> observableShapeType;
    private SimpleObjectProperty<Color> observableColor;
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
//    public Color getColor() {
//        return this.color;
//    }
    public double getSize() {
        return this.size;
    }
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
    public Shape getHighlightedShape() {
        return this.highlightedShape;
    }
    public List<Shape> getToRender() {
        return this.toRender;
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
//    public void setColor(Color color) {
//        this.color = color;
//    }
    //TODO Rewrite this method to parse a string and return a dimension which will be a list of size 2 , if list[0] == null then something is worng, if list[1] == null then it is either a size or a radius.
    public void setSize(double size) {
        this.size = size;
    }
    public void setWidthAndHeightFromDimension(String dimension) {
        try {
            String[] dimensionArray = dimension.split("x");
            this.width = Double.parseDouble(dimensionArray[0]);
            this.height = Double.parseDouble(dimensionArray[1]);
        } catch (Exception e) {
            System.out.println("Dimension field is empty");
        }
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE GETTERS/SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public ObservableList<Shape> getObservableList() {
        return observableList;
    }
    public ShapeType getObservableShapeType() {
        return observableShapeType.getValue();
    }
    public Color getObservableColor() {
        return observableColor.get();
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




    public void setObservableShapeType(ShapeType shapeType) {
        this.observableShapeType.set(shapeType);
    }
    public void setObservableColor(Color color) {
        this.observableColor.set(color);
    }
    public void setObservableRadius(double radius) {
        this.observableRadius.set(radius);
    }
    public void setObservableSize(double size) {
        this.observableSize.set(size);
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

    public ObjectProperty<ShapeType> shapeTypeProperty() {
        return observableShapeType;
    }
    public ObjectProperty<Color> colorProperty() {
        return observableColor;
    }
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
        toStore = new ArrayList<>();
        shapeFactory = new ShapeFactory();

        ObservableList<Shape> observableList = FXCollections.observableList(toRender);

        Shape defaultShape = shapeFactory.shapeBuilder(null,null,0.0,0.0,0.0,0.0,0.0);
        highlight(defaultShape);

        observableToRender = new SimpleListProperty<>();
        observableShapeType = new SimpleObjectProperty<>();
        observableColor = new SimpleObjectProperty<>(Color.RED);
        observableRadius = new SimpleDoubleProperty();
        observableSize = new SimpleDoubleProperty();
        observableDimension = new SimpleStringProperty("125x25");
        observableWidth = new SimpleDoubleProperty();
        observableHeight = new SimpleDoubleProperty();
        observableMouseX = new SimpleDoubleProperty();
        observableMouseY = new SimpleDoubleProperty();


    }

    public Shape newShape() {

         return shapeFactory.shapeBuilder(this.getType(), this.getObservableColor(), this.getSize(), this.getWidth(), this.getHeight(), this.getMouseX(), this.getMouseY());


    }


//    public Shape newDefaultShape() {
//
//        return shapeFactory.shapeBuilder(null,null,0.0,0.0,0.0,0.0,0.0);
//
//    }
    private void highlight(Shape highlightedShape) {
        this.highlightedShape = highlightedShape;
    }
    public void assignShapeToRender(Shape shape) {
        this.toRender.add(shape);
        highlight(shape);
    }
    public void render(GraphicsContext gc) {

        System.out.println("Nr of objects to render: " + toRender.size());

        if(!toRender.isEmpty()) {

            for(Shape shape:toRender) {

                shape.toDisplay(gc);
                System.out.println(shape.toSvg());

            }

            //highlight(toRender.get(toRender.size()-1));
        }
        System.out.println(highlightedShape + " is highlighted");
    }
    public void selector(double mouseClickX, double mouseClickY) {

        System.out.println("Nr of objects to render: " + toRender.size());

        List<Shape> tempList = new ArrayList<>();
        int tempIndex = 0;

        if(!toRender.isEmpty()) {

            for(int i = 0; i < toRender.size(); i++) {
                if (toRender.get(i).isSelected(mouseClickX, mouseClickY)) {

                    tempList.add(toRender.get(i));
                    tempIndex = i;

                }
            }

            if (!tempList.isEmpty()) {
                highlight(tempList.get(tempList.size()-1));
                System.out.println(highlightedShape + "is selected");
            }
            }

        indexOfHighlightedShape = tempIndex;

        }
    public void undo() {
        if(!toRender.isEmpty()) {
            toStore.add(toRender.get(toRender.size()-1));
            toRender.remove(toRender.size()-1);
        }
    }
    public void redo() {
        if (!toStore.isEmpty()) {
            toRender.add(toStore.get(toStore.size()-1));
            toStore.remove(toStore.size()-1);
        }
    }
    public void clearCanvas(Canvas context) {
        context.getGraphicsContext2D().clearRect(0, 0, context.getWidth(), context.getHeight());
    }
    public void saveToFile(Path file) {
        StringBuffer outPut = new StringBuffer();

//        for (int i = 1; i < toRender.size(); i++) {
//            outPut.append(toRender.get(i).toSvg());
//            outPut.append("\n");
//        }

        for (Shape shape:toRender) {
            outPut.append(shape.toSvg());
            outPut.append("\n");
        }

        try {
            Files.writeString(file, outPut.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }






    //private createShape(double mouseX, double mouseY, Color color, double radius) {}


    //#####################################################################################################################




















    // Magisk klass som uppdateras så fort view uppdateras
    StringProperty message = new SimpleStringProperty();



    // En observerbar lista


    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }


//    public void sendMessage() {
//        getObservableList().add(getMessage());
//        setMessage("");
//    }










}
