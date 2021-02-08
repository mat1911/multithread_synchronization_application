package muczynski.mateusz.storehousesManagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import muczynski.mateusz.storehouses.FirstStorehouse;
import muczynski.mateusz.storehouses.OutputStorehouse;
import muczynski.mateusz.storehouses.SecondStorehouse;
import muczynski.mateusz.products.Product;
import muczynski.mateusz.storehouses.Storehouse;
import muczynski.mateusz.window.animations.IngredientsAnimation;
import muczynski.mateusz.window.animations.ProductAnimation;
import muczynski.mateusz.window.animations.ProductionLineAnimation;

import java.util.Random;
import java.util.concurrent.Semaphore;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionLine extends Thread{
    private final int MAX_PRODUCE_TIME = 10000;
    private FirstStorehouse firstStorehouse;
    private SecondStorehouse secondStorehouse;
    private OutputStorehouse outputStorehouse;
    private Semaphore accessToFirstStorehouse;
    private Semaphore accessToSecondStorehouse;
    private Semaphore accessToOutputStorehouse;
    private Semaphore iWantToTakeIngredient1;
    private Semaphore iWantToTakeIngredient2;
    private Semaphore iWantToSendProduct;
    private int numberOfProductionLine;

    private  IngredientsAnimation ingredientsAnimation;

    @Override
    public void run(){
        while(true) {
            wait(1000);
            produce();
        }
    }

    private void produce(){
        String ingredient1 = getIngredient(firstStorehouse, accessToFirstStorehouse, iWantToTakeIngredient1);
        String ingredient2 = getIngredient(secondStorehouse, accessToSecondStorehouse, iWantToTakeIngredient2);
        Product product = new Product(ingredient1, ingredient2);
        Random time = new Random();

        wait(time.nextInt(MAX_PRODUCE_TIME) + 2000);
        addToOutputStorehouse(product);
    }

    private void addToOutputStorehouse(Product product){
        ProductAnimation productAnimation = new ProductAnimation();
        ProductionLineAnimation productionLineAnimation = new ProductionLineAnimation();
        boolean isAdded = false;

        productionLineAnimation.productionLineWaitToSend(numberOfProductionLine);
        semaphoreDown(iWantToSendProduct);

        while(outputStorehouse.getProducts().size() == outputStorehouse.getSTORAGE_CAPACITY()){}

        productionLineAnimation.productionLineRun(numberOfProductionLine);

        semaphoreDown(accessToOutputStorehouse);
        while (!isAdded){ isAdded = outputStorehouse.addProduct(product);}

        ingredientsAnimation.transformIngrToProd();
        productAnimation.moveProductToStorehouse(numberOfProductionLine);
        semaphoreUp(accessToOutputStorehouse);
        semaphoreUp(iWantToSendProduct);
    }

    private String getIngredient(Storehouse storehouse, Semaphore accessToStorehouse, Semaphore accessProductionLine){

        String ingredient = null;

        semaphoreDown(accessProductionLine);
        while(storehouse.getIngredients().size() == 2){}

        semaphoreDown(accessToStorehouse);

        while(ingredient == null){
            ingredient = storehouse.takeIngredient();
        }

        ingredientsAnimation.moveIngredient(storehouse, numberOfProductionLine);

        semaphoreUp(accessToStorehouse);
        semaphoreUp(accessProductionLine);

        return ingredient;
    }

    private void wait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void semaphoreDown(Semaphore semaphore){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void semaphoreUp(Semaphore semaphore){
        semaphore.release();
    }
}
