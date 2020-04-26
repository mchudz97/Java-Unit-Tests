package proj2.entities;

public class Client {

    private String name;
    private String surname;

    public void setName(String name){

        if(name == null || !name.matches("[A-Z][a-z]{2,254}")){

            throw new IllegalArgumentException("Invalid name value!");

        }

        this.name = name;

    }

    public void setSurname(String surname){

        if(name == null || !name.matches("[A-Z][a-z]{2,254}")){

            throw new IllegalArgumentException("Invalid name value!");

        }

        this.surname = surname;

    }

}
