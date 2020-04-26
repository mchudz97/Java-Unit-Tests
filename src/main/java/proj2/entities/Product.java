package proj2.entities;

public class Product {

    private String name;



    public void setName(String name){




        if(name == null || !name.matches("([A-Z][a-z]{1,244}[ ]?)+")){

            throw new IllegalArgumentException("Invalid product name!");

        }

        this.name = name;

    }

}
