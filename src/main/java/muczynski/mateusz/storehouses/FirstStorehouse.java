package muczynski.mateusz.storehouses;

import lombok.Getter;
import muczynski.mateusz.storehouses.enums.StorehouseNames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

@Getter
public class FirstStorehouse implements Storehouse {
    private final int STORAGE_CAPACITY;
    private volatile List<String> ingredients;
    private Semaphore accessToStorehouse;
    private StorehouseNames storehouseName;

    public FirstStorehouse(int STORAGE_CAPACITY) {
        this.STORAGE_CAPACITY = STORAGE_CAPACITY;
        this.accessToStorehouse = new Semaphore(1);
        this.ingredients = new ArrayList<>();
        this.storehouseName = StorehouseNames.FIRST;

        initIngr();
    }

    @Override
    public boolean addIngredient(String ingredient){
        if(ingredients.size() < STORAGE_CAPACITY){
            ingredients.add(ingredient);
            return true;
        }
        return false;
    }

    @Override
    public String takeIngredient(){
        System.out.println(ingredients.size());
        String ingredient;
        if(!ingredients.isEmpty()){
            ingredient = ingredients.get(0);
            ingredients.remove(ingredient);
            return ingredient;
        }
        return null;
    }

    private void initIngr(){
        for (int i = 0; i < STORAGE_CAPACITY; i++) {
            ingredients.add("FIRST_INGR");
        }
    }
}
