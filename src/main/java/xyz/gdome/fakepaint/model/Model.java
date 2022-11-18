package xyz.gdome.fakepaint.model;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
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
    private double size;
    private double width;
    private double height;
    private double mouseX;
    private double mouseY;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private Shape testShape = new Circle(Color.FIREBRICK, 35, 150, 150);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE FIELDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private SimpleObjectProperty<Color> observableColor;
    private SimpleDoubleProperty observableSize;
    private SimpleStringProperty observableDimension;

    public ObservableList<Shape> getObservableToRender() {
        return observableToRender;
    }

    public void setObservableToRender(ObservableList<Shape> observableToRender) {
        this.observableToRender = observableToRender;
    }

    public ObservableList<Shape> observableToRender = FXCollections.observableArrayList();

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

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public List<Shape> getToRender() {
        return this.observableToRender;
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


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE GETTERS/SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Color getObservableColor() {
        return observableColor.get();
    }

    public double getObservableSize() {
        return observableSize.getValue();
    }

    public void setObservableColor(Color color) {
        this.observableColor.set(color);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OBSERVABLE PROPERTIES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public ObjectProperty<Color> colorProperty() {
        return observableColor;
    }

    public DoubleProperty sizeProperty() {
        return observableSize;
    }

    public StringProperty dimensionProperty() {
        return observableDimension;
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Model() {

        toRender = new ArrayList<>();
        toStore = new ArrayList<>();
        shapeFactory = new ShapeFactory();


        Shape defaultShape = shapeFactory.shapeBuilder(null, null, 0.0, 0.0, 0.0, 0.0, 0.0);
        highlight(defaultShape);

        observableColor = new SimpleObjectProperty<>(Color.RED);
        observableSize = new SimpleDoubleProperty();
        observableDimension = new SimpleStringProperty("125x25");







    }

    public Shape newShape() {

        return shapeFactory.shapeBuilder(this.getType(), this.getObservableColor(), this.getObservableSize(), this.getWidth(), this.getHeight(), this.getMouseX(), this.getMouseY());

    }

    private void highlight(Shape highlightedShape) {
        this.highlightedShape = highlightedShape;
        indexOfHighlightedShape = observableToRender.size() - 1;
    }

    public void assignShapeToRender(Shape shape) {

        if (!shape.getClass().getSimpleName().equals("DefaultShape")) {
            this.observableToRender.add(shape);
            highlight(shape);
        }

    }

    public void render(GraphicsContext gc) {

        if (!observableToRender.isEmpty()) {

            for (Shape shape : observableToRender) {

                shape.toDisplay(gc);


            }
        }
    }

    private void svgToShape(String svgString) {

        ShapeType type = ShapeType.DEFAULT;
        Color color = Color.BLACK;
        double width = 0.0;
        double height = 0.0;
        double size = 0.0;
        double x = 0.0;
        double y = 0.0;

        Shape constructedShape;

        String[] splitString = svgString.split(" ");

        if (splitString[1].equals("<rect")) {

            x = Double.parseDouble(splitString[2].split("=")[1].substring(1, 4));
            y = Double.parseDouble(splitString[3].split("=")[1].substring(1, 4));
            width = Double.parseDouble(splitString[4].split("=")[1].substring(1, 5));
            height = Double.parseDouble(splitString[5].split("=")[1].substring(1, 5));
            color = Color.valueOf(splitString[6].split("=")[1].substring(1, 10));


            if (width == height) {
                type = ShapeType.SQUARE;
                size = width;
            } else {
                type = ShapeType.RECTANGLE;
            }
        }

        if (splitString[1].equals("<circle")) {

            x = Double.parseDouble(splitString[2].split("=")[1].substring(1, 4));
            y = Double.parseDouble(splitString[3].split("=")[1].substring(1, 4));
            size = Double.parseDouble(splitString[4].split("=")[1].substring(1, 5)) * 2;
            color = Color.valueOf(splitString[5].split("=")[1].substring(1, 10));
            type = ShapeType.CIRCLE;
        }

        constructedShape = shapeFactory.shapeBuilder(type, color, size, width, height, x, y);
        System.out.println(constructedShape);

        assignShapeToRender(constructedShape);


    }


    public void selector(double mouseClickX, double mouseClickY) {

        List<Shape> tempList = new ArrayList<>();

        int tempIndex = 0;

        if (!observableToRender.isEmpty()) {

            for (int i = 0; i < observableToRender.size(); i++) {
                if (observableToRender.get(i).isSelected(mouseClickX, mouseClickY)) {

                    tempList.add(observableToRender.get(i));
                    tempIndex = i;

                }
            }

            if (!tempList.isEmpty()) {
                highlight(tempList.get(tempList.size() - 1));
            }
        }
        indexOfHighlightedShape = tempIndex;

        System.out.println(observableToRender.get(indexOfHighlightedShape));
    }

    public void undo() {
        if (!observableToRender.isEmpty()) {
            toStore.add(observableToRender.get(observableToRender.size() - 1));
            observableToRender.remove(observableToRender.size() - 1);
        }
    }

    public void redo() {
        if (!toStore.isEmpty()) {
            observableToRender.add(toStore.get(toStore.size() - 1));
            toStore.remove(toStore.size() - 1);
        }
    }

    public void clearCanvas(Canvas context) {
        context.getGraphicsContext2D().clearRect(0, 0, context.getWidth(), context.getHeight());
    }

    public void saveToSVG(Path file) {
        StringBuffer outPut = new StringBuffer();

        outPut.append("<svg version=\"1.1\"\n" +
                "     width=\"700\" height=\"475\"\n" +
                "     xmlns=\"http://www.w3.org/2000/svg\">");

        for (Shape shape : observableToRender) {
            outPut.append(shape.toSvg());
            outPut.append("\n");
        }
        outPut.append("\n" +
                "</svg>");

        try {
            Files.writeString(file, outPut.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateServer() {

        writer.println(highlightedShape.toSvg());

    }

    public void runServer() {
        try {
            socket = new Socket("localhost", 8000);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Ingen serveranslutning");
        }

        new Thread(() -> {
            try {
                while (true) {
                    String line = reader.readLine();
                    Platform.runLater(() -> svgToShape(line));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }).start();


    }


}


