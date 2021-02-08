package muczynski.mateusz.storehousesManagement;

import lombok.AllArgsConstructor;
import muczynski.mateusz.storehouses.OutputStorehouse;
import muczynski.mateusz.window.animations.ProductAnimation;

import java.util.Random;
import java.util.concurrent.Semaphore;

@AllArgsConstructor
public class Exporter extends Thread{
    private final int MAX_WAITING_TIME = 10000;
    private final int NUMBER_OF_EXPORTED_PRODUCTS;
    private OutputStorehouse outputStorehouse;
    private Semaphore accessToOutputStorehouse;

    @Override
    public void run(){
        Random draw = new Random();
        while (true) {
            wait(draw.nextInt(MAX_WAITING_TIME) + 5000);
            export();
        }
    }

    private void export(){
        ProductAnimation productAnimation = new ProductAnimation();

        semaphoreDown(accessToOutputStorehouse);
        for (int i = 0; i < NUMBER_OF_EXPORTED_PRODUCTS; i++) {
            if(outputStorehouse.getProduct() != null){
                productAnimation.moveProductFromStorehouse();
                wait(600);
            }
        }
        semaphoreUp(accessToOutputStorehouse);
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
