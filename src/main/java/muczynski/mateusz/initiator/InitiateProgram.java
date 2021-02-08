package muczynski.mateusz.initiator;

import muczynski.mateusz.storehouses.FirstStorehouse;
import muczynski.mateusz.storehouses.OutputStorehouse;
import muczynski.mateusz.storehouses.SecondStorehouse;
import muczynski.mateusz.storehousesManagement.Exporter;
import muczynski.mateusz.storehousesManagement.ProductionLine;
import muczynski.mateusz.storehousesManagement.Supplier;
import muczynski.mateusz.window.animations.IngredientsAnimation;
import muczynski.mateusz.window.initiators.ProductionLinesInitiator;
import muczynski.mateusz.window.initiators.StorehouseInitiator;

import java.util.concurrent.Semaphore;

public class InitiateProgram {
    private final int FIRST_STOREHOUSE_CAPACITY = 40; //MAX 40
    private final int SECOND_STOREHOUSE_CAPACITY = 40; // MAX 40
    private final int OUTPUT_STOREHOUSE_CAPACITY = 21; // MAX 21
    private final int NUMBER_OF_PRODUCTION_LINES = 9; //MAX 9
    private final int CAPACITY_OF_CAR = 16;
    private final int NUMBER_OF_EXPORTED_PRODUCTS = 6;

    private FirstStorehouse firstStorehouse;
    private SecondStorehouse secondStorehouse;
    private OutputStorehouse outputStorehouse;

    private Semaphore accessToFirstStorehouse;
    private Semaphore accessToSecondStorehouse;
    private Semaphore accessToOutputStorehouse;
    private Semaphore iWantToTakeIngredient1;
    private Semaphore iWantToTakeIngredient2;
    private Semaphore iWantToSendProduct;

    private Supplier supplier;
    private Exporter exporter;
    private ProductionLine[] productionLines;


    public InitiateProgram() {
        initAllElements();
        startAllProcesses();
    }

    private void startAllProcesses(){
        this.supplier.start();

        for (int i = 0; i < NUMBER_OF_PRODUCTION_LINES; i++) {
            this.productionLines[i].start();
        }

        this.exporter.start();
    }

    private void initAllElements(){
        initStorehouses();
        initSemaphores();
        initStorehouesManagement();
        initProductionLines();
    }

    private void initStorehouses(){
        StorehouseInitiator storehouseInitiator = new StorehouseInitiator(FIRST_STOREHOUSE_CAPACITY, SECOND_STOREHOUSE_CAPACITY);

        this.firstStorehouse = new FirstStorehouse(FIRST_STOREHOUSE_CAPACITY);
        this.secondStorehouse = new SecondStorehouse(SECOND_STOREHOUSE_CAPACITY);
        this.outputStorehouse = new OutputStorehouse(OUTPUT_STOREHOUSE_CAPACITY);

        storehouseInitiator.initiateStorehouses();
    }

    private void initSemaphores(){
        this.accessToFirstStorehouse = new Semaphore(1);
        this.accessToSecondStorehouse = new Semaphore(1);
        this.accessToOutputStorehouse = new Semaphore(1);
        this.iWantToTakeIngredient1 = new Semaphore(1);
        this.iWantToTakeIngredient2 = new Semaphore(1);
        this.iWantToSendProduct = new Semaphore(1);
    }

    private void initStorehouesManagement(){
        this.exporter = new Exporter(NUMBER_OF_EXPORTED_PRODUCTS, outputStorehouse, accessToOutputStorehouse);
        this.supplier = new Supplier(CAPACITY_OF_CAR, firstStorehouse, secondStorehouse, accessToFirstStorehouse, accessToSecondStorehouse);
    }

    private void initProductionLines(){
        ProductionLinesInitiator productionLinesInitiator = new ProductionLinesInitiator();

        this.productionLines = new ProductionLine[NUMBER_OF_PRODUCTION_LINES];

        for (int i = 0; i < NUMBER_OF_PRODUCTION_LINES; i++) {
            this.productionLines[i] = ProductionLine
                    .builder()
                    .firstStorehouse(firstStorehouse)
                    .secondStorehouse(secondStorehouse)
                    .outputStorehouse(outputStorehouse)
                    .accessToFirstStorehouse(accessToFirstStorehouse)
                    .accessToSecondStorehouse(accessToSecondStorehouse)
                    .accessToOutputStorehouse(accessToOutputStorehouse)
                    .iWantToTakeIngredient1(iWantToTakeIngredient1)
                    .iWantToTakeIngredient2(iWantToTakeIngredient2)
                    .iWantToSendProduct(iWantToSendProduct)
                    .ingredientsAnimation(new IngredientsAnimation())
                    .numberOfProductionLine(i)
                    .build();
        }

        productionLinesInitiator.initiateProductionLines(NUMBER_OF_PRODUCTION_LINES);
    }
}
