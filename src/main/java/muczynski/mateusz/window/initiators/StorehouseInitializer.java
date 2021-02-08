package muczynski.mateusz.window.initiators;

import javafx.scene.layout.GridPane;
import muczynski.mateusz.window.GridPaneContent;
import muczynski.mateusz.window.WindowData;

public class StorehouseInitializer {
    private final int FIRST_STOREHOUSE_CAPACITY;
    private final int SECOND_STOREHOUSE_CAPACITY;
    private GridPaneContent gridPaneContent;


    public StorehouseInitializer(int FIRST_STOREHOUSE_CAPACITY, int SECOND_STOREHOUSE_CAPACITY) {
        this.FIRST_STOREHOUSE_CAPACITY = FIRST_STOREHOUSE_CAPACITY;
        this.SECOND_STOREHOUSE_CAPACITY = SECOND_STOREHOUSE_CAPACITY;
        this.gridPaneContent = new GridPaneContent();
    }

    public void initiateStorehouses(){
        GridPane firstStorehouse = (GridPane) WindowData.scene.lookup("#firstStorehouse");
        GridPane secondStorehouse = (GridPane) WindowData.scene.lookup("#secondStorehouse");

        initStorehouse(firstStorehouse, FIRST_STOREHOUSE_CAPACITY, 4);
        initStorehouse(secondStorehouse, SECOND_STOREHOUSE_CAPACITY, 4);
    }

    private void initStorehouse(GridPane storehouse, int capacity, int numberOfColumns){
        int row = storehouse.getRowConstraints().size() - 1;
        int col = 0;

        while (capacity > 0) {
            gridPaneContent.getLabel(storehouse, col++, row).setVisible(true);
            capacity--;

            if(col % numberOfColumns == 0){
                col = 0;
                row--;
            }
        }
    }
}
