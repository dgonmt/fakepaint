package xyz.gdome.fakepaint.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xyz.gdome.fakepaint.model.Model;
import xyz.gdome.fakepaint.model.ShapeType;

import java.io.File;

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
    @FXML
    private GraphicsContext context;
    @FXML
    private Canvas canvas;
    private Stage stage;

    Model model = new Model();


    @FXML
    private void initialize() {

        context = canvas.getGraphicsContext2D();
        dimensionField.textProperty().bindBidirectional(model.dimensionProperty());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        sizeSlider.valueProperty().bindBidirectional(model.sizeProperty());

    }


    @FXML
    private void onCanvasClicked(MouseEvent mouseEvent) {

        if (mouseEvent.isControlDown()) {

            model.setMouseX(mouseEvent.getX());
            model.setMouseY(mouseEvent.getY());
            model.selector(mouseEvent.getX(), mouseEvent.getY());
            model.updateServer();



        } else {

            model.setMouseX(mouseEvent.getX());
            model.setMouseY(mouseEvent.getY());
            model.setWidthAndHeightFromDimension(dimensionField.getText());
            model.assignShapeToRender(model.newShape());
            model.render(context);
            model.updateServer();


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
    private void colorValueChange() {
            try {
                model.clearCanvas(canvas);
                model.getToRender().get(model.indexOfHighlightedShape).setColor(model.getObservableColor());
                model.render(context);

            } catch (Exception e) {

            }
    }

    @FXML
    private void sizeValueChange() {
        try {
            model.clearCanvas(canvas);
            model.getToRender().get(model.indexOfHighlightedShape).setSize(model.getObservableSize());
            model.render(context);

        } catch (Exception e) {

        }
    }

    @FXML
    private void saveToFile(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));

        File filePath = fileChooser.showSaveDialog(stage);

        if (filePath != null) {
            model.saveToSVG(filePath.toPath());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}