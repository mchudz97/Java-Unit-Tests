package proj2.tests.easymock;

import DB.DBdriver;
import DB.EmailChecker;
import DB.Shop;
import com.thoughtworks.qdox.model.expression.Or;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proj2.entities.Client;
import proj2.entities.Order;
import org.easymock.*;
import proj2.entities.Product;
import proj2.myMocks.AdminToolsFake;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.easymock.EasyMock.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class ShopTest {

    private DBdriver dBdriver;
    @TestSubject
    private Shop shop;


    @BeforeEach
    public void reset(){

        dBdriver = createMock(MockType.STRICT, DBdriver.class);
        shop = new Shop(dBdriver);

    }


    @Test
    @DisplayName("Update null order")
    public void updateNullOrder(){

        Order order = null;


        assertThatThrownBy(() -> shop.updateOrder(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot update null object!");

    }

    @Test
    @DisplayName("Update non existing order")
    public void updateNonExistingOrder(){

        Order orderMock = createMock(Order.class);
        expect(orderMock.getId()).andReturn(0);
        replay(orderMock);
        expect(dBdriver.getOrderById(0)).andReturn(null);
        replay(dBdriver);

        assertThatThrownBy(() -> shop.updateOrder(orderMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("That order doesnt exist!");

    }

    @Test
    @DisplayName("Update order")
    public void updateOrder(){

        Order orderMock = createMock(Order.class);
        expect(orderMock.getId()).andReturn(0);
        expect(dBdriver.getOrderById(0)).andReturn(createMock(Order.class));
        dBdriver.updateOrder(orderMock);
        expectLastCall();
        replay(dBdriver, orderMock);

        shop.updateOrder(orderMock);

        verify();

    }

    @Test
    @DisplayName("Get order by id when id is negative")
    public void getOrderByIdWhenNegative(){

        assertThatThrownBy(() -> shop.getOrderById(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid id value!");

    }

    @Test
    @DisplayName("Get order by id when order doesnt exist")
    public void getOrderByIdWhenNull(){

        expect(dBdriver.getOrderById(0)).andReturn(null);
        replay(dBdriver);

        assertThatThrownBy(() -> shop.getOrderById(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order with that id doesnt exist!");

    }

    @Test
    @DisplayName("Get order by id")
    public void getOrderById(){

        Order orderMock = createMock(Order.class);
        expect(dBdriver.getOrderById(0)).andReturn(orderMock);
        replay(dBdriver);

        assertThat(shop.getOrderById(0), is(orderMock));

    }

    @Test
    @DisplayName("Get all orders when null client")
    public void getAllOrdersWhenNull(){

        Client client = null;

        assertThatThrownBy(() -> shop.getAllOrdersFrom(client))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client is  null!");

    }

    @Test
    @DisplayName("Get all orders when non existing client")
    public void getAllOrdersWhenNonExisting(){

        Client clientMock = createMock(MockType.NICE, Client.class);
        expect(clientMock.getId()).andReturn(0);
        expect(dBdriver.getClientById(0)).andReturn(null);
        replay(clientMock, dBdriver);

        assertThatThrownBy(() -> shop.getAllOrdersFrom(clientMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client doesnt exist!");

    }

    @Test
    @DisplayName("Get all orders from client")
    public void getAllOrdersFromClient(){

        Client clientMock = createMock(MockType.NICE, Client.class);
        expect(clientMock.getId()).andReturn(0);
        expect(dBdriver.getClientById(0)).andReturn(createMock(Client.class));
        expect(dBdriver.getAllOrdersFrom(clientMock)).andReturn(null);
        replay(clientMock, dBdriver);
        shop.getAllOrdersFrom(clientMock);

        verify();

    }

    @Test
    @DisplayName("Get all products from null order")
    public void getAllProductsWhenNull(){

        Order order = null;

        assertThatThrownBy(() -> shop.getAllProductsFrom(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order is  null!");

    }

    @Test
    @DisplayName("Get all products when non existing order")
    public void getAllProductsWhenNonExisting(){

        Order orderMock = createMock(MockType.NICE, Order.class);
        expect(orderMock.getId()).andReturn(0);
        expect(dBdriver.getOrderById(0)).andReturn(null);
        replay(orderMock, dBdriver);

        assertThatThrownBy(() -> shop.getAllProductsFrom(orderMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order doesnt exist!");

    }

    @Test
    @DisplayName("Get all products from order")
    public void getAllProductsFromOrder(){

        Order orderMock = createMock(MockType.NICE, Order.class);
        expect(orderMock.getId()).andReturn(0);
        expect(dBdriver.getOrderById(0)).andReturn(createMock(Order.class));
        expect(dBdriver.getAllProductsFrom(orderMock)).andReturn(null);
        replay(orderMock, dBdriver);
        shop.getAllProductsFrom(orderMock);

        verify();

    }

    @Test
    @DisplayName("Get all orders from null product")
    public void getAllOrdersFromNull(){

        Product product = null;

        assertThatThrownBy(() -> shop.getAllOrdersFrom(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product is  null!");


    }

    @Test
    @DisplayName("Get all orders from non existing product")
    public void getAllOrderFromNonExisting(){

        Product productMock = createMock(MockType.DEFAULT, Product.class);
        expect(productMock.getId()).andReturn(0);
        expect(dBdriver.getProductById(0)).andReturn(null);
        replay(productMock, dBdriver);

        assertThatThrownBy(() -> shop.getAllOrdersFrom(productMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product doesnt exist!");

    }

    @Test
    @DisplayName("Get all orders from product")
    public void getAllOrdersFromProduct(){

        Product productMock = createMock(MockType.DEFAULT, Product.class);
        expect(productMock.getId()).andReturn(0);
        expect(dBdriver.getProductById(0)).andReturn(createMock(Product.class));
        expect(dBdriver.getAllOrdersFrom(productMock)).andReturn(null);
        replay(productMock, dBdriver);
        shop.getAllOrdersFrom(productMock);

        verify();

    }

    @Test
    @DisplayName("Send email to inactive email adress")
    public void sendToInactive(){

        Client clientMock = createMock(MockType.NICE, Client.class);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true);
        expect(clientMock.getEmailAdress()).andReturn("invalid@email.com");
        expect(emailCheckerMock.isActive("invalid@email.com")).andReturn(false);
        replay(clientMock, emailCheckerMock);

        assertThatThrownBy(() -> shop.sendEmailFromTo("a", clientMock, "d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid@email.com is not valid adress.");


    }

    @Test
    @DisplayName("Send email from inactive email adress")
    public void sendFromInactive(){


        Client clientMock = createMock(MockType.NICE, Client.class);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(false);

        replay(emailCheckerMock);

        assertThatThrownBy(() -> shop.sendEmailFromTo("a", clientMock, "d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("a is not valid adress.");


    }

    @Test
    @DisplayName("Send email with blank description")
    public void sendEmailWithBlankDescription(){

        Client clientMock = createMock(MockType.NICE, Client.class);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true);
        expect(clientMock.getEmailAdress()).andReturn("invalid@email.com");
        expect(emailCheckerMock.isActive("invalid@email.com")).andReturn(true);
        replay(clientMock, emailCheckerMock);

        assertThatThrownBy(() -> shop.sendEmailFromTo("a", clientMock, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid description.");

    }

    @Test
    @DisplayName("Send email with null description")
    public void sendEmailWithNullDescription(){

        Client clientMock = createMock(MockType.NICE, Client.class);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true);
        expect(clientMock.getEmailAdress()).andReturn("invalid@email.com");
        expect(emailCheckerMock.isActive("invalid@email.com")).andReturn(true);
        replay(clientMock, emailCheckerMock);

        assertThatThrownBy(() -> shop.sendEmailFromTo("a", clientMock, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid description.");

    }

    @Test
    @DisplayName("Send email")
    public void sendEmail(){

        Client clientMock = createMock(MockType.NICE, Client.class);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true);
        expect(clientMock.getEmailAdress()).andReturn("invalid@email.com");
        expect(emailCheckerMock.isActive("invalid@email.com")).andReturn(true);
        replay(clientMock, emailCheckerMock);

        shop.sendEmailFromTo("a", clientMock, "d");
        verify();

    }

    @Test
    @DisplayName("Send broadcast email if receiver is inactive adress")
    public void sendBroadcastToInactive(){

        Client clientMock1 = createMock(MockType.NICE, Client.class);
        Client clientMock2 = createMock(MockType.NICE, Client.class);
        expect(clientMock1.getEmailAdress()).andReturn("valid@email.com");
        expect(clientMock2.getEmailAdress()).andReturn("invalid@email.com");
        List<Client> listMock = createMock(MockType.STRICT, List.class);
        expect(listMock.get(0)).andReturn(clientMock1);
        expect(listMock.get(1)).andReturn(clientMock2);
        expect(dBdriver.getAllClients()).andReturn(listMock);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(dBdriver, adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true).times(2);
        expect(emailCheckerMock.isActive("valid@email.com")).andReturn(true);
        expect(emailCheckerMock.isActive("invalid@email.com")).andReturn(false);
        replay(clientMock1, clientMock2, emailCheckerMock, dBdriver, listMock);

        assertThatThrownBy(() -> shop.sendBroadcastEmailFrom("a", "d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid@email.com is not valid adress.");


    }


    @Test
    @DisplayName("Send broadcast email if sender is inactive adress")
    public void sendBroadcastFromInactive(){

        Client clientMock1 = createMock(MockType.NICE, Client.class);
        Client clientMock2 = createMock(MockType.NICE, Client.class);
        expect(clientMock1.getEmailAdress()).andReturn("valid@email.com");
        List<Client> listMock = createMock(MockType.STRICT, List.class);
        expect(listMock.get(0)).andReturn(clientMock1);
        expect(dBdriver.getAllClients()).andReturn(listMock);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(dBdriver, adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(false).times(1);

        replay(clientMock1, listMock, emailCheckerMock, dBdriver);

        assertThatThrownBy(() -> shop.sendBroadcastEmailFrom("a", "d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("a is not valid adress.");


    }

    @Test
    @DisplayName("Send broadcast email if description is blank")
    public void sendBroadcastWithBlankDescription(){

        Client clientMock1 = createMock(MockType.NICE, Client.class);
        Client clientMock2 = createMock(MockType.NICE, Client.class);
        expect(clientMock1.getEmailAdress()).andReturn("valid@email.com");
        List<Client> listMock = createMock(MockType.STRICT, List.class);
        expect(listMock.get(0)).andReturn(clientMock1);
        expect(dBdriver.getAllClients()).andReturn(listMock);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(dBdriver, adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true).times(1);

        replay(clientMock, emailCheckerMock);

        assertThatThrownBy(() -> shop.sendBroadcastEmailFrom("a", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid description.");


    }

    @Test
    @DisplayName("Send broadcast email if description is null")
    public void sendBroadcastWithNullDescription(){

        Client clientMock1 = createMock(MockType.NICE, Client.class);
        Client clientMock2 = createMock(MockType.NICE, Client.class);
        expect(clientMock1.getEmailAdress()).andReturn("valid@email.com");
        List<Client> listMock = createMock(MockType.STRICT, List.class);
        expect(listMock.get(0)).andReturn(clientMock1);
        expect(dBdriver.getAllClients()).andReturn(listMock);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(dBdriver, adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true).times(1);

        replay(clientMock, emailCheckerMock);

        assertThatThrownBy(() -> shop.sendBroadcastEmailFrom("a", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid description.");


    }

    @Test
    @DisplayName("Send broadcast email ")
    public void sendBroadcast(){

        Client clientMock1 = createMock(MockType.NICE, Client.class);
        Client clientMock2 = createMock(MockType.NICE, Client.class);
        expect(clientMock1.getEmailAdress()).andReturn("valid@email.com");
        expect(clientMock2.getEmailAdress()).andReturn("invalid@email.com");
        List<Client> listMock = createMock(MockType.STRICT, List.class);
        expect(listMock.get(0)).andReturn(clientMock1).times(1);
        expect(listMock.get(1)).andReturn(clientMock2).times(1);
        expect(dBdriver.getAllClients()).andReturn(listMock).times(1);
        EmailChecker emailCheckerMock = createMock(EmailChecker.class);
        AdminToolsFake adminToolsFake = new AdminToolsFake(emailCheckerMock);
        shop = new Shop(dBdriver, adminToolsFake);
        expect(emailCheckerMock.isActive("a")).andReturn(true).times(2);
        expect(emailCheckerMock.isActive("valid@email.com")).andReturn(true).times(1);
        expect(emailCheckerMock.isActive("invalid@email.com")).andReturn(true).times(1);
        replay(clientMock1, clientMock2, emailCheckerMock, dBdriver, listMock);

        verify();


    }


}
