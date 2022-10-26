package xyz.gdome.fakepaint.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import xyz.gdome.fakepaint.model.Model;

import java.util.ArrayList;

public class Controller {

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

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}