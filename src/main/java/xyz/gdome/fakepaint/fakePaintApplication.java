package xyz.gdome.fakepaint;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.gdome.fakepaint.controller.Controller;

import java.io.IOException;

public class fakePaintApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(fakePaintApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 812.5);

        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("dumbPaint");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}