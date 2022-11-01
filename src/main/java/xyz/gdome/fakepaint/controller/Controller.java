package xyz.gdome.fakepaint.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import xyz.gdome.fakepaint.model.Model;
import xyz.gdome.fakepaint.model.ShapeFactory;
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
    @FXML
    private ColorPicker colorPicker;



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

        //TODO
        /**TODO All the bindings between the GUI controllers and observable properties should be done here.
         * TODO bind the controllers to the highlighted shape
         */

        dimensionField.textProperty().bindBidirectional(model.dimensionProperty());


        gc = context.getGraphicsContext2D();
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
            System.out.println("CNTRL+Click");
        } else {
            System.out.println("Click");
            model.setMouseX(mouseEvent.getSceneX());
            model.setMouseY(mouseEvent.getSceneY());
            System.out.println(mouseEvent.getSceneX());
            System.out.println(mouseEvent.getSceneY());

            model.setWidthAndHeightFromDimension(dimensionField.getText());
            model.assignShapeToRender(model.newShape());
            model.render(gc);


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

    @FXML
    private void selectColor() {
        model.setColor(colorPicker.getValue());
    }

}