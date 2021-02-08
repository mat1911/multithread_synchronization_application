package muczynski.mateusz.window.animations;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import muczynski.mateusz.storehouses.enums.StorehouseNames;
import muczynski.mateusz.window.WindowData;

public class ProductAnimation extends IngredientsAnimation {

    public void moveProductToStorehouse(int index) {

        Label label = createLabel("PRODUCT", "#FF8F2D");

        Pane[] mainPane = new Pane[1];

        Platform.runLater(() -> {
            mainPane[0] = (Pane) WindowData.scene.lookup("#mainPane");
            mainPane[0].getChildren().add(label);
        });

        Path path = new Path();
        path.getElements().add(new MoveTo(910, 235 + index * 51));
        path.getElements().add(new LineTo(1003, 355));

        PathTransition transition = createPathTransition(label, path, 2);
        transition.onFinishedProperty().set((e) -> {
            addElementToStorehouse(StorehouseNames.OUTPUT, 2);
            mainPane[0].getChildren().remove(label);
        });
        transition.play();
    }

    public void moveProductFromStorehouse() {

        getElementFromStorehouse(StorehouseNames.OUTPUT, 2);
        Label label = createLabel("PRODUCT", "#FF8F2D");

        Pane[] mainPane = new Pane[1];

        Platform.runLater(() -> {
            mainPane[0] = (Pane) WindowData.scene.lookup("#mainPane");
            mainPane[0].getChildren().add(label);
        });

        Path path = new Path();
        path.getElements().add(new MoveTo(1109, 247));
        path.getElements().add(new LineTo(1109, 0));

        PathTransition transition = createPathTransition(label, path, 2);
        transition.onFinishedProperty().set((e) -> {
            mainPane[0].getChildren().remove(label);
        });
        transition.play();
    }
}
