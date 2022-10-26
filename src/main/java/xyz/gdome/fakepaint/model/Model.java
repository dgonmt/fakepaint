package xyz.gdome.fakepaint.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Model {

    ArrayList<Shape> toRender;
    private double mouseX;
    private double mouseY;

    private Color color;

    private double width;

    private double height;

    private double radius;

    private ShapeType type;

    public Model() {
        toRender = new ArrayList<>();
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


    public void setMouseX(double x) {
        this.mouseX = x;
    }

    public void setMouseY(double y) {
        this.mouseY = y;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void buildShape(double mouseX, double mouseY, Color color, )


}
