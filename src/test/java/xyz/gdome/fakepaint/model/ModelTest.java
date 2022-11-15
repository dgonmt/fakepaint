package xyz.gdome.fakepaint.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void createNewShapeAddsNewShapeToToRenderList() {

        Model model = new Model();

        model.setMouseX(80);
        model.setMouseY(80);
        model.setObservableColor(Color.ANTIQUEWHITE);
        model.setSize(40);
        model.setType(ShapeType.CIRCLE);

        model.assignShapeToRender(model.newShape());

        assertEquals(1, model.getToRender().size());
        assertEquals(
                new Circle(Color.ANTIQUEWHITE, 40, 80, 80),
                model.getToRender().get(0));

    }

}