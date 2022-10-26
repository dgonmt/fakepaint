package xyz.gdome.fakepaint.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import xyz.gdome.fakepaint.model.Model;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Canvas context;
    @FXML
    private GraphicsContext gc;


    @FXML
    private void initialize() {

        //toolSelection.setOnAction(this::selectShape);
        Model model = new Model();

        gc = context.getGraphicsContext2D();
        gc.setFill(Color.KHAKI);
        gc.fillRect(0,0,850,800);
    }
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}