package DB;

import proj2.entities.Client;

public class Shop {

    private DBdriver dBdriver;

    public Shop(DBdriver dBdriver){

        this.setDBdriver(dBdriver);

    }


    public DBdriver getDBdriver() {
        return dBdriver;
    }

    public void setDBdriver(DBdriver dBdriver) {
        this.dBdriver = dBdriver;
    }

    public void addClient(Client client){

        if(client == null){

            throw new IllegalArgumentException("Cannot add null object");

        }

        if (this.dBdriver.getClientById(client.getId()) != null){

            throw new IllegalArgumentException("Client with that id already exist!");

        }

        this.dBdriver.addClient(client);

    }

    public void removeClient(Client client){

        if(client == null){

            throw new IllegalArgumentException("Cannot remove null object");

        }

        if (this.dBdriver.getClientById(client.getId()) == null){

            throw new IllegalArgumentException("Client with that id doesnt exist!");

        }

        this.dBdriver.removeClient(client);

    }

}
