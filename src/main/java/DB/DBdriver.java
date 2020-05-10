package DB;

import proj2.entities.Client;
import proj2.entities.Order;
import proj2.entities.Product;

public interface DBdriver {

    void addClient(Client client);
    Client getClientById(int id);
    void removeClient(Client client);
    void updateClient(Client client);
    void addProduct(Product product);
    Product getProductById(int id);
    void removeProduct(Product product);
    void updateProduct(Product product);
    void addOrder(Order order);
    Order getOrderById(int id);
    void removeOrder(Order order);


}
