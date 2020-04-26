package proj2.entities;

public class Client {

    private String name;
    private String surname;
    private int id;
    private String emailAdress;

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

    public void setId(int id){

        if(id < 0){

            throw new IllegalArgumentException("Id value must be positive!");

        }

        this.id = id;

    }

    public void setEmailAdress(String emailAdress){

        if(emailAdress == null || !emailAdress.matches("[a-z]{1,32}[@][a-z]{1,32}[.][a-z]{2,3}")){

            throw new IllegalArgumentException("Invalid email adress!");

        }

        this.emailAdress = emailAdress;

    }


}
