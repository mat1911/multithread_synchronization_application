package muczynski.mateusz.storehouses;

import muczynski.mateusz.storehouses.enums.StorehouseNames;

import java.util.List;

public interface Storehouse {
    List<String> getIngredients();
    String takeIngredient();
    StorehouseNames getStorehouseName();
    boolean addIngredient(String ingredient);
}
