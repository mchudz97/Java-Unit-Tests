package proj2.entities;

public class Product {

    private String name;
    private int id;
    private float price;

    public void setName(String name){




        if(name == null || !name.matches("([A-Z][a-z]{1,244}[ ]?)+")){

            throw new IllegalArgumentException("Invalid product name!");

        }

        this.name = name;

    }

    public void setId(int id){

        if(id < 0){

            throw new IllegalArgumentException("Id value must be positive");

        }

        this.id = id;

    }

    public void setPrice(float price){

        if(price < 0){

            throw new IllegalArgumentException("Price value must be positive");

        }

        this.price = price;

    }


}
