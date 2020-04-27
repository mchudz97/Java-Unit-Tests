package proj2.entities;

public class Order {

    private int id;


    public void setId(int id){

        if(id<0){

            throw new IllegalArgumentException("Id must be positive!");

        }

        this.id = id;

    }

}
