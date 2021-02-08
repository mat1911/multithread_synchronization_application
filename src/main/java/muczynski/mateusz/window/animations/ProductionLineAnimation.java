package muczynski.mateusz.window.animations;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import muczynski.mateusz.window.GridPaneContent;
import muczynski.mateusz.window.WindowData;

public class ProductionLineAnimation {

    private GridPaneContent gridPaneContent;

    public ProductionLineAnimation() {
        this.gridPaneContent = new GridPaneContent();
    }

    public void productionLineWaitToSend(int numberOfProductionLine){
        GridPane productionLines = (GridPane)WindowData.scene.lookup("#produceLines");
        Label productionLine = gridPaneContent.getLabel(productionLines, 0, numberOfProductionLine);

        Platform.runLater(() -> {
            productionLine.setStyle("-fx-background-color: #FF3200");
        });
    }

    public void productionLineRun(int numberOfProductionLine){
        GridPane productionLines = (GridPane)WindowData.scene.lookup("#produceLines");
        Label productionLine = gridPaneContent.getLabel(productionLines, 0, numberOfProductionLine);

        Platform.runLater(() -> {
            productionLine.setStyle("-fx-background-color: #FFFFFF");
        });
    }
}
