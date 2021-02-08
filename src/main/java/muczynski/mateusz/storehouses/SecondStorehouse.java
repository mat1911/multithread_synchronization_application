package muczynski.mateusz.storehouses;

import lombok.Getter;
import muczynski.mateusz.storehouses.enums.StorehouseNames;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SecondStorehouse implements Storehouse {
    private final int STORAGE_CAPACITY;
    private volatile List<String> ingredients;
    private StorehouseNames storehouseName;

    public SecondStorehouse(int STORAGE_CAPACITY) {
        this.STORAGE_CAPACITY = STORAGE_CAPACITY;
        this.ingredients = new ArrayList<>();
        this.storehouseName = StorehouseNames.SECOND;

        initIngr();
    }

    @Override
    public boolean addIngredient(String ingredient){
        if(this.ingredients.size() < STORAGE_CAPACITY){
            this.ingredients.add(ingredient);
            return true;
        }
        return false;
    }

    @Override
    public String takeIngredient(){
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
            ingredients.add("SECOND_INGR");
        }
    }
}
