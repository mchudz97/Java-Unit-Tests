package DB;

import com.thoughtworks.qdox.model.expression.Or;
import proj2.entities.Client;
import proj2.entities.Order;
import proj2.entities.Product;

import java.util.List;

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
    void updateOrder(Order order);
    List<Order> getAllOrdersFrom(Client client);
    List<Product> getAllProductsFrom(Order order);
    List<Order> getAllOrdersFrom(Product product);
    List<Client> getAllClients();


}
