package proj2.entities;

public class Client {

    private String name;
    private String surname;
    private int id;
    private String emailAdress;

    public Client(){};

    public Client(int id, String name, String surname, String emailAdress){

        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setEmailAdress(emailAdress);

    }

    public void setName(String name){

        if(name == null || !name.matches("[A-Z][a-z]{2,31}")){

            throw new IllegalArgumentException("Invalid name value!");

        }

        this.name = name;

    }

    public void setSurname(String surname){

        if(name == null || !name.matches("[A-Z][a-z]{2,31}")){

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

    public String getName() {

        return name;

    }

    public String getSurname() {

        return surname;

    }

    public int getId() {

        return id;

    }

    public String getEmailAdress() {

        return emailAdress;

    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + "\tName: " + this.getName() + "\tSurname: "
                + this.getSurname() + "\tEmail adress: " + this.getEmailAdress();
    }
}
