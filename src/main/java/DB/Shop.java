package DB;

import proj2.entities.Client;
import proj2.entities.Order;
import proj2.entities.Product;

import java.util.List;

public class Shop {

    private DBdriver dBdriver;
    private AdminTools adminTools;

    public Shop(DBdriver dBdriver){

        this.setDBdriver(dBdriver);

    }

    public Shop(AdminTools adminTools){

        this.adminTools = adminTools;

    }

    public Shop(DBdriver dBdriver, AdminTools adminTools){

        this.adminTools = adminTools;
        this.dBdriver = dBdriver;

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

    public void updateClient(Client client){

        if(client == null){

            throw new IllegalArgumentException("Cannot update null object");

        }

        if (this.dBdriver.getClientById(client.getId()) == null){

            throw new IllegalArgumentException("Client with that id doesnt exist!");

        }

        this.dBdriver.updateClient(client);

    }

    public Client getClientById(int id){

        if(id < 0){

            throw new IllegalArgumentException("Invalid id value!");

        }

        Client found = this.dBdriver.getClientById(id);
        if(found == null){

            throw new IllegalArgumentException("Client with that id doesnt exist!");

        }

        return found;

    }

    public void addProduct(Product product){

        if(product == null){

            throw new IllegalArgumentException("Cannot add null object");

        }

        if (this.dBdriver.getProductById(product.getId()) != null){

            throw new IllegalArgumentException("That product already exists!");

        }

        this.dBdriver.addProduct(product);

    }

    public void removeProduct(Product product){

        if(product == null){

            throw new IllegalArgumentException("Cannot remove null object!");

        }

        if (this.dBdriver.getProductById(product.getId()) == null){

            throw new IllegalArgumentException("Product with that id doesnt exist!");

        }

        this.dBdriver.removeProduct(product);

    }

    public void updateProduct(Product product){

        if(product == null){

            throw new IllegalArgumentException("Cannot update null object!");

        }

        if (this.dBdriver.getProductById(product.getId()) == null){

            throw new IllegalArgumentException("Product with that id doesnt exist!");

        }

        this.dBdriver.updateProduct(product);

    }

    public Product getProductById(int id){

        if(id < 0){

            throw new IllegalArgumentException("Invalid id value!");

        }

        Product found = this.dBdriver.getProductById(id);
        if(found == null){

            throw new IllegalArgumentException("Product with that id doesnt exist!");

        }

        return found;

    }

    public void addOrder(Order order){

        if(order == null){

            throw new IllegalArgumentException("Cannot add null object!");

        }

        if (this.dBdriver.getOrderById(order.getId()) != null){

            throw new IllegalArgumentException("That order already exists!");

        }

        this.dBdriver.addOrder(order);

    }

    public void removeOrder(Order order){

        if(order == null){

            throw new IllegalArgumentException("Cannot remove null object!");

        }

        if (this.dBdriver.getOrderById(order.getId()) == null){

            throw new IllegalArgumentException("That order doesnt exist!");

        }

        this.dBdriver.removeOrder(order);

    }

    public void updateOrder(Order order){

        if(order == null){

            throw new IllegalArgumentException("Cannot update null object!");

        }

        if (this.dBdriver.getOrderById(order.getId()) == null){

            throw new IllegalArgumentException("That order doesnt exist!");

        }

        this.dBdriver.updateOrder(order);

    }

    public Order getOrderById(int id){

        if(id < 0){

            throw new IllegalArgumentException("Invalid id value!");

        }

        Order found = this.dBdriver.getOrderById(id);
        if(found == null){

            throw new IllegalArgumentException("Order with that id doesnt exist!");

        }

        return found;

    }

    public List<Order> getAllOrdersFrom(Client client){

        if(client == null){

            throw new IllegalArgumentException("Client is  null!");

        }

        if(this.dBdriver.getClientById(client.getId()) == null){

            throw new IllegalArgumentException("Client doesnt exist!");

        }


        return dBdriver.getAllOrdersFrom(client);

    }

    public List<Product> getAllProductsFrom(Order order){

        if(order == null){

            throw new IllegalArgumentException("Order is  null!");

        }

        if(this.dBdriver.getOrderById(order.getId()) == null){

            throw new IllegalArgumentException("Order doesnt exist!");

        }

        return dBdriver.getAllProductsFrom(order);

    }

    public List<Order> getAllOrdersFrom(Product product){

        if(product == null){

            throw new IllegalArgumentException("Product is  null!");

        }

        if(this.dBdriver.getProductById( product.getId()) == null){

            throw new IllegalArgumentException("Product doesnt exist!");

        }


        return dBdriver.getAllOrdersFrom(product);

    }

    public void sendEmailFromTo(String sender, Client receiver, String description){


        this.adminTools.sendMessage(sender, receiver, description);

    }

    public void sendBroadcastEmailFrom(String sender, String description){

        List<Client> clients = this.dBdriver.getAllClients();
        if(clients == null){

            throw new IllegalArgumentException("No clients found.");

        }

        adminTools.sendBroadcastMessage(sender, clients, description);

    }


}
