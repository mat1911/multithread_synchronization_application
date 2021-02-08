package muczynski.mateusz.storehousesManagement;

import lombok.AllArgsConstructor;
import muczynski.mateusz.storehouses.FirstStorehouse;
import muczynski.mateusz.storehouses.SecondStorehouse;
import muczynski.mateusz.storehouses.Storehouse;
import muczynski.mateusz.window.WindowData;
import muczynski.mateusz.window.animations.IngredientsAnimation;
import muczynski.mateusz.window.animations.TruckAnimation;

import java.util.concurrent.Semaphore;

@AllArgsConstructor
public class Supplier extends Thread{
    private final int MIN_QUANTITY_OF_PRODUCTS1 = 2;
    private final int MIN_QUANTITY_OF_PRODUCTS2 = 2;
    private final int CAPACITY_OF_CAR;
    private FirstStorehouse firstStorehouse;
    private SecondStorehouse secondStorehouse;
    private Semaphore accessToFirstStorehouse;
    private Semaphore accessToSecondStorehouse;

    @Override
    public void run(){
        TruckAnimation truckAnimation = new TruckAnimation(WindowData.scene);

        while(true){
            wait(4000);

            if(isDeliveryNecessary(firstStorehouse, MIN_QUANTITY_OF_PRODUCTS1)){
                semaphoreDown(accessToFirstStorehouse);
                truckAnimation.firstTruckIsComing();

                wait(3000);
                addIngredients(firstStorehouse, "FIRST_ING");
                truckAnimation.firstTruckIsLeaving();
                semaphoreUp(accessToFirstStorehouse);
            }


            if(isDeliveryNecessary(secondStorehouse, MIN_QUANTITY_OF_PRODUCTS2)){
                semaphoreDown(accessToSecondStorehouse);
                truckAnimation.secondTruckIsComing();
                wait(3000);
                addIngredients(secondStorehouse, "SECOND_ING");
                truckAnimation.secondTruckIsLeaving();
                semaphoreUp(accessToSecondStorehouse);
            }
        }
    }

    private void addIngredients(Storehouse storehouse, String ingredientName){
        int i = 0;
        boolean storehouseIsNotFull = true;
        IngredientsAnimation ingredientsAnimation = new IngredientsAnimation();

        while(i < CAPACITY_OF_CAR && storehouseIsNotFull){
            ingredientsAnimation.addElementToStorehouse(storehouse.getStorehouseName(), 3);
            storehouseIsNotFull = storehouse.addIngredient(ingredientName);
            i++;
        }
    }

    private boolean isDeliveryNecessary(Storehouse storehouse, int minValue){
        if(storehouse.getIngredients().size() <= minValue){
            return true;
        }
        return false;
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
