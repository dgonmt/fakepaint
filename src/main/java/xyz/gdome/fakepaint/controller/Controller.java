package xyz.gdome.fakepaint.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xyz.gdome.fakepaint.model.Model;
import xyz.gdome.fakepaint.model.ShapeFactory;
import xyz.gdome.fakepaint.model.ShapeType;

import java.io.File;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Button generateSquareBtn;
    @FXML
    private Button generateRectangleBtn;
    @FXML
    private Button generateCircleBtn;
    @FXML
    private Button undoBtn;
    @FXML
    private Button redoBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField dimensionField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider sizeSlider;


    Model model = new Model();
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext context;
    public Stage stage;

    public ListView<String> messagesListView;



    public TextField messageField;

    public Button sendButton;

    @FXML
    private void initialize() {

        //TODO
        /**TODO All the bindings between the GUI controllers and observable properties should be done here.
         * TODO bind the controllers to the highlighted shape
         */

        context = canvas.getGraphicsContext2D();
        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                model.setSize(sizeSlider.getValue());
            }
        });
        dimensionField.textProperty().bindBidirectional(model.dimensionProperty());


        colorPicker.valueProperty().bindBidirectional(model.colorProperty());

        colorPicker.valueProperty().addListener((observableValue, color, t1) -> {
            try {
                model.getToRender().get(model.indexOfHighlightedShape).setColor(model.getObservableColor());
                model.render(context);
            } catch (Exception e) {
                System.out.println("Nothing to change");
            }
        });





        model.render(context);


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
//        context = canvas.getGraphicsContext2D();


    }

//    public void sendMessageClicked() {
//        //Handle button action
//
//        //
//        model.sendMessage();
//
//    }



    @FXML
    private void onCanvasClicked(MouseEvent mouseEvent) {
        if (mouseEvent.isControlDown()) {

            System.out.println("CNTRL+Click");

            model.setMouseX(mouseEvent.getX());
            model.setMouseY(mouseEvent.getY());
            model.selector(mouseEvent.getX(), mouseEvent.getY());

//            System.out.println("Mouse X: " + mouseEvent.getX());
//            System.out.println("Mouse Y: " + mouseEvent.getY());





        } else {

            System.out.println("Click");
            model.setMouseX(mouseEvent.getX());
            model.setMouseY(mouseEvent.getY());

            model.setWidthAndHeightFromDimension(dimensionField.getText());
            model.assignShapeToRender(model.newShape());
            model.render(context);

            System.out.println("Mouse X: " + mouseEvent.getX());
            System.out.println("Mouse Y: " + mouseEvent.getY());



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
    private void undo() {
        model.undo();
        model.clearCanvas(canvas);
        model.render(context);
    }
    @FXML
    private void redo() {
        model.redo();
        model.clearCanvas(canvas);
        model.render(context);
    }

    @FXML
    private void selectColor() {
        //model.setColor(colorPicker.getValue());
        //model.renderSelected(context);
    }
    @FXML
    private void saveToFile(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));

        File filePath = fileChooser.showSaveDialog(stage);

        if (filePath != null) {
            model.saveToFile(filePath.toPath());
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }



}