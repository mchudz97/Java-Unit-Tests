package proj2.entities;

import com.thoughtworks.qdox.model.expression.Or;

public class Order {

    private int id;
    private int clientId;

    public Order(){};

    public Order(int selfId, int clientId){

        this.setId(selfId);
        this.setClientId(clientId);

    }

    public void setId(int id){

        if(id < 0){

            throw new IllegalArgumentException("Id must be positive!");

        }

        this.id = id;

    }

    public void setClientId(int id){

        if(id < 0){

            throw new IllegalArgumentException("Id must be positive!");

        }

        this.clientId = id;

    }

    public int getId() {

        return id;

    }

    public int getClientId() {

        return clientId;

    }

    @Override
    public String toString() {

        return "Id: " + this.getId() + "\tClient id: " + this.getClientId() + "\n";

    }
}
