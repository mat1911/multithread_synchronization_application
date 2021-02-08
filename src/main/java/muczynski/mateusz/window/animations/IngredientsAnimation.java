package muczynski.mateusz.window.animations;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;
import muczynski.mateusz.storehouses.enums.StorehouseNames;
import muczynski.mateusz.storehouses.Storehouse;
import muczynski.mateusz.window.GridPaneContent;
import muczynski.mateusz.window.WindowData;

public class IngredientsAnimation {

    private Label first;
    private Label second;

    public void moveIngredient(Storehouse storehouse, int numberOfProductionLine) {
        switch (storehouse.getStorehouseName()) {
            case FIRST:
                moveFirstIngredient(numberOfProductionLine);
                break;
            case SECOND:
                moveSecondIngredient(numberOfProductionLine);
                break;
        }
    }

    public GridPane getStorehouse(StorehouseNames name) {
        switch (name) {
            case FIRST:
                return (GridPane) WindowData.scene.lookup("#firstStorehouse");
            case SECOND:
                return (GridPane) WindowData.scene.lookup("#secondStorehouse");
            case OUTPUT:
                return (GridPane) WindowData.scene.lookup("#outputStorehouse");
            default:
                return null;
        }
    }

    public boolean addElementToStorehouse(StorehouseNames storehouseName, int numberOfColumns) {
        GridPaneContent gridPaneContent = new GridPaneContent();
        GridPane storehouse = getStorehouse(storehouseName);
        int row = storehouse.getRowConstraints().size() - 1, col = 0, firstRow = 0;
        Label freeSpace;

        do {
            freeSpace = gridPaneContent.getLabel(storehouse, col++, row);
            if (col > numberOfColumns) {
                col = 0;
                row--;
            }
        } while (row > firstRow && freeSpace.isVisible());

        if (!freeSpace.isVisible()) {
            freeSpace.setVisible(true);
            return true;
        }
        return false;
    }

    public void transformIngrToProd(){
        Pane mainPane = (Pane) WindowData.scene.lookup("#mainPane");
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            mainPane.getChildren().remove(first);
        });

        Platform.runLater(() -> {
            mainPane.getChildren().remove(second);
        });
    }

    private void moveFirstIngredient(int index) {

        getElementFromStorehouse(StorehouseNames.FIRST, 3);

        first = createLabel("FIRST_INGR", "#24FF96");

        Pane mainPane = (Pane) WindowData.scene.lookup("#mainPane");
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            mainPane.getChildren().add(first);
        });

        Path path = new Path();
        path.getElements().add(new MoveTo(573, 158));
        path.getElements().add(new LineTo(705, 235 + index * 51));

        PathTransition transition = createPathTransition(first, path, 2);
        transition.onFinishedProperty().set((e) -> transition.stop());
        transition.play();
    }


    private void moveSecondIngredient(int index) {

        getElementFromStorehouse(StorehouseNames.SECOND, 3);

        second = createLabel("SECOND_INGR", "#A3EEFF");

        Pane mainPane = (Pane) WindowData.scene.lookup("#mainPane");
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            mainPane.getChildren().add(second);
        });

        Path path = new Path();
        path.getElements().add(new MoveTo(583, 705));
        path.getElements().add(new LineTo(910, 235 + index * 51));

        PathTransition transition = createPathTransition(second, path, 2);
        transition.play();
    }

    protected boolean getElementFromStorehouse(StorehouseNames storehouseName, int lastColumn) {
        GridPaneContent gridPaneContent = new GridPaneContent();
        GridPane storehouse = getStorehouse(storehouseName);
        int row = 1, col = lastColumn, lastRow = storehouse.getRowConstraints().size() - 1;
        Label ownedIngredient;

        do {
            ownedIngredient = gridPaneContent.getLabel(storehouse, col--, row);
            if (col < 0) {
                col = lastColumn;
                row++;
            }
        } while (row <= lastRow && !ownedIngredient.isVisible());

        if (ownedIngredient.isVisible()) {
            ownedIngredient.setVisible(false);
            return true;
        }
        return false;
    }

    protected PathTransition createPathTransition(Node node, Path path, int duration) {

        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setNode(node);
        transition.setDuration(Duration.seconds(duration));

        return transition;
    }

    protected Label createLabel(String text, String color) {

        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(100);
        label.setPrefHeight(29);
        label.setStyle("-fx-background-color: " + color + "; -fx-border-color: black; -fx-border-width: 2");
        label.setVisible(true);

        return label;
    }
}
