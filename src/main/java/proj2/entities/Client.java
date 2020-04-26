package proj2.entities;

public class Client {

    private String name;

    public void setName(String name){

        if(name == null || !name.matches("[A-Z][a-z]{2,254}")){

            throw new IllegalArgumentException("Invalid name value!");

        }

        this.name = name;

    }

}
