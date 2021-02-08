package muczynski.mateusz.storehouses;

import lombok.Getter;
import muczynski.mateusz.products.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OutputStorehouse {
    private final int STORAGE_CAPACITY;
    private volatile List<Product> products;

    public OutputStorehouse(int STORAGE_CAPACITY) {
        this.STORAGE_CAPACITY = STORAGE_CAPACITY;
        this.products = new ArrayList<>();
    }

    public boolean addProduct(Product product){

        if(products.size() < STORAGE_CAPACITY){
            products.add(product);
            return true;
        }
        return false;
    }

    public Product getProduct(){
        Product product;
        if(!products.isEmpty()){
            product = products.get(0);
            products.remove(product);
            return product;
        }
        return null;
    }

}
