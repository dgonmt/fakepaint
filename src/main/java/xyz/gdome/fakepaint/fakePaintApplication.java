package xyz.gdome.fakepaint;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class fakePaintApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(fakePaintApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 812.5);
        stage.setTitle("dumbPaint");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}