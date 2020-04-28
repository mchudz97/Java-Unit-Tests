package proj2.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {

    private List<Product> products = new ArrayList<Product>();

    public void add(Product product){

        if(product == null) throw new IllegalArgumentException("Product has null value!");

        for (Product p: products) {

            if(p.getId() == product.getId()){

                throw new IllegalArgumentException("That product already exists!");

            }

        }

        this.products.add(product);

    }

    public List getProducts(){

        return products;

    }

    public void remove(Product product){

        if(product == null) throw new IllegalArgumentException("Product has null value!");

        for(Product p : products){

            if(p.getId() == product.getId()){

                this.products.remove(p);
                return;

            }

        }

        throw new IllegalArgumentException("Product not found!");

    }

    public void removeAll(){

        this.products.removeAll(products);

    }

}
