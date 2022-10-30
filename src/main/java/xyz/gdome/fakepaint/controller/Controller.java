package xyz.gdome.fakepaint.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import xyz.gdome.fakepaint.model.Model;
import xyz.gdome.fakepaint.model.ShapeType;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Button generateSquareBtn;
    @FXML
    private Button generateRectangleBtn;
    @FXML
    private Button generateCircleBtn;
    @FXML
    private TextField dimensionField;

    Model model = new Model();
    @FXML
    private Canvas context;
    @FXML
    private GraphicsContext gc;

    public ListView<String> messagesListView;


    public TextField messageField;

    public Button sendButton;

    @FXML
    private void initialize() {

        //TODO All the bindings between the GUI controllers and observable properties should be done here.
        dimensionField.textProperty().bindBidirectional(model.dimensionProperty());



        Model model = new Model();

        gc = context.getGraphicsContext2D();
        gc.setFill(Color.KHAKI);
        gc.fillRect(0, 0, 200, 200);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,200,200);

        model.render(gc);


        // Här kopplar vi ihop view med kontrollern

        // Kopplingen till messageProperty i modellen


//        messageField.textProperty().bindBidirectional(model.messageProperty());
//
//        messagesListView.setItems(model.getObservableList());
//
//
//
//        sendButton.disabledProperty().bind(model.messageProperty().isEmpty());
//
//        gc = context.getGraphicsContext2D();


    }

    public void sendMessageClicked() {
        //Handle button action

        //
        model.sendMessage();

    }

    @FXML
    private Label welcomeText;

    //TODO Implement functionality so that if a shape is selected make it listen for new data until regular onClick on canvas
    @FXML
    private void onCanvasClicked(MouseEvent mouseEvent) {
        if (mouseEvent.isControlDown()) {
            //Select the shape clicked on
        }
    }

    @FXML
    private void squareBtn() {
        model.setType(ShapeType.SQUARE);
    }
    @FXML
    private void rectangleBtn() {
        model.setType(ShapeType.RECTANGLE);
    }
    @FXML
    private void circleBtn() {
        model.setType(ShapeType.CIRCLE);
    }
}