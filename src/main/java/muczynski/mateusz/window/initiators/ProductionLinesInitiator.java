package muczynski.mateusz.window.initiators;

import javafx.scene.layout.GridPane;
import muczynski.mateusz.window.GridPaneContent;
import muczynski.mateusz.window.WindowData;

public class ProductionLinesInitiator {
    private GridPaneContent gridPaneContent;

    public ProductionLinesInitiator() {
        this.gridPaneContent = new GridPaneContent();
    }

    public void initiateProductionLines(int numberOfProduceLines){
        GridPane productionLines = (GridPane) WindowData.scene.lookup("#produceLines");
        int row = 0;
        int col = 0;

        while (numberOfProduceLines > 0) {
            gridPaneContent.getLabel(productionLines, col, row++).setVisible(true);
            numberOfProduceLines--;
        }
    }

}
