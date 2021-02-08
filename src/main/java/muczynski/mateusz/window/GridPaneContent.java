package muczynski.mateusz.window;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GridPaneContent {

    public Label getLabel(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node) != null
                    && GridPane.getColumnIndex(node) == col
                    && GridPane.getRowIndex(node) == row) {
                return (Label) node;
            }
        }
        return null;
    }
}
