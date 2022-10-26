module xyz.gdome.fakepaint {
    requires javafx.controls;
    requires javafx.fxml;


    opens xyz.gdome.fakepaint to javafx.fxml;
    exports xyz.gdome.fakepaint;
    exports xyz.gdome.fakepaint.controller;
    opens xyz.gdome.fakepaint.controller to javafx.fxml;
}