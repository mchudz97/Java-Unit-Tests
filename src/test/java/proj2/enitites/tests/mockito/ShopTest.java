package proj2.enitites.tests.mockito;

import DB.DBdriver;
import DB.Shop;
import Matchers.Mockito.ClientMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import proj2.entities.Client;
import proj2.entities.Order;
import proj2.entities.Product;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class ShopTest {

    private DBdriver dBdriver;

    @InjectMocks
    private Shop shop;

    private void setupDbMock(){

        dBdriver = mock(DBdriver.class);

    }

    @BeforeEach
    private void reset(){

        dBdriver = mock(DBdriver.class);
        shop = new Shop(dBdriver);

    }

    @Test
    @DisplayName("Shop initialization test")
    public void initTest(){

        assertThat(shop.getDBdriver(), is(dBdriver));

    }

    @Test
    @DisplayName("Add Client if client with the same id exist")
    public void addWhenExists(){

        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(0);
        when(dBdriver.getClientById(0)).thenReturn(mock(Client.class));

        assertThatThrownBy(() -> shop.addClient(clientMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client with that id already exist!");

    }

    @Test
    @DisplayName("Add Client if client is null")
    public void addWhenNull(){

        Client client = null;

        assertThatThrownBy(() -> shop.addClient(client))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot add null object");

    }

    @Test
    @DisplayName("Add Client")
    public void addClient(){

        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(0);
        when(dBdriver.getClientById(0)).thenReturn(null);
        final boolean[] isAdded = {false};
        doAnswer(new Answer<Void>(){

            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                isAdded[0] = true;
                return null;

            }

        }).when(dBdriver).addClient(clientMock);
        shop.addClient(clientMock);

        assertThat(isAdded[0], is(true)); // taki sobie przyklad dla voidow

    }

    @Test
    @DisplayName("Remove client when client is null")
    public void removeWhenNull(){

        Client client = null;

        assertThatThrownBy(() -> shop.removeClient(client))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot remove null object");

    }

    @Test
    @DisplayName("Remove client when client doesnt exist")
    public void removeWhenDoesntExist(){

        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(0);
        when(dBdriver.getClientById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.removeClient(clientMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client with that id doesnt exist!");

    }

    @Test
    @DisplayName("Remove client")
    public void removeClient(){

        Client clientMock = mock(Client.class);
        when(dBdriver.getClientById(0)).thenReturn(clientMock);
        doNothing().when(dBdriver).removeClient(clientMock);
        shop.removeClient(clientMock);

        verify(dBdriver, times(1)).removeClient(clientMock); // no mozna i tak

    }

    @Test
    @DisplayName("Update null client")
    public void updateWhenNull(){

        Client client = null;

        assertThatThrownBy(() -> shop.updateClient(client))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot update null object");

    }

    @Test
    @DisplayName("Update non existing client")
    public void updateNonExisting(){

        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(0);
        when(dBdriver.getClientById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.updateClient(clientMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client with that id doesnt exist!");

    }

    @Test
    @DisplayName("Update client")
    public void updateClient(){

        Client clientMock = mock(Client.class);
        when(dBdriver.getClientById(0)).thenReturn(clientMock);
        doNothing().when(dBdriver).updateClient(clientMock);
        shop.updateClient(clientMock);

        verify(dBdriver, times(1)).updateClient(clientMock);

    }

    @Test
    @DisplayName("Get client by id when no client with that id")
    public void getClientByIdWhenNoClient(){

        when(dBdriver.getClientById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.getClientById(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client with that id doesnt exist!");

    }

    @Test
    @DisplayName("Get client by id when id value is negative")
    public void getClientByIdWhenNegative(){

        assertThatThrownBy(() -> shop.getClientById(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid id value!");

    }

    private Client clientEquals(Client expected){
        return argThat(new ClientMatcher(expected));
    }

    @Test
    @DisplayName("Get client by id")
    public void getClientById(){

        Client clientMock = mock(Client.class);
        when(dBdriver.getClientById(0)).thenReturn(clientMock);

        assertThat(shop.getClientById(0), is(clientMock));

    }

    @Test
    @DisplayName("add product when product is null")
    public void addProductWhenNull(){

        Product product = null;
        assertThatThrownBy(() -> shop.addProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot add null object");

    }

    @Test
    @DisplayName("add product if product with the same id already exists")
    public void addProductWhenExists(){

        Product productMock = mock(Product.class);
        when(dBdriver.getProductById(0)).thenReturn(mock(Product.class));
        assertThatThrownBy(() -> shop.addProduct(productMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("That product already exists!");


    }

    @Test
    @DisplayName("add product")
    public void addProduct(){

        Product productMock = mock(Product.class);
        when(dBdriver.getProductById(0)).thenReturn(null);
        doNothing().when(dBdriver).addProduct(productMock);
        shop.addProduct(productMock);

        verify(dBdriver, times(1)).addProduct(productMock);

    }

    @Test
    @DisplayName("Remove null product")
    public void removeNullProduct(){

        Product product = null;

        assertThatThrownBy(() -> shop.removeProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot remove null object!");

    }

    @Test
    @DisplayName("Remove non existing product")
    public void removeNonExistingProduct(){

        Product productMock = mock(Product.class);
        when(productMock.getId()).thenReturn(0);
        when(dBdriver.getProductById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.removeProduct(productMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with that id doesnt exist!");

    }

    @Test
    @DisplayName("Remove product")
    public void removeProduct(){

        Product productMock = mock(Product.class);
        when(productMock.getId()).thenReturn(0);
        when(dBdriver.getProductById(0)).thenReturn(mock(Product.class));
        doNothing().when(dBdriver).removeProduct(productMock);
        shop.removeProduct(productMock);

        verify(dBdriver, times(1)).removeProduct(productMock);

    }

    @Test
    @DisplayName("Update null product")
    public void updateNullProduct(){

        Product product = null;

        assertThatThrownBy(() -> shop.updateProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot update null object!");

    }

    @Test
    @DisplayName("Update non existing product")
    public void UpdateNonExistingProduct(){

        Product productMock = mock(Product.class);
        when(productMock.getId()).thenReturn(0);
        when(dBdriver.getProductById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.updateProduct(productMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with that id doesnt exist!");

    }

    @Test
    @DisplayName("Update product")
    public void updateProduct(){

        Product productMock = mock(Product.class);
        when(productMock.getId()).thenReturn(0);
        when(dBdriver.getProductById(0)).thenReturn(mock(Product.class));
        doNothing().when(dBdriver).updateProduct(productMock);
        shop.updateProduct(productMock);

        verify(dBdriver, times(1)).updateProduct(productMock);

    }

    @Test
    @DisplayName("Get client by id when no product with that id")
    public void getProductByIdWhenNoClient(){

        when(dBdriver.getProductById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.getProductById(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with that id doesnt exist!");

    }

    @Test
    @DisplayName("Get product by id when id value is negative")
    public void getProductByIdWhenNegative(){

        assertThatThrownBy(() -> shop.getProductById(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid id value!");

    }


    @Test
    @DisplayName("Get product by id")
    public void getProductById(){

        Product productMock = mock(Product.class);
        when(dBdriver.getProductById(0)).thenReturn(productMock);

        assertThat(shop.getProductById(0), is(productMock));

    }

    @Test
    @DisplayName("Add Order when order is null")
    public void addOrderWhenNull(){

        Order order = null;

        assertThatThrownBy(() -> shop.addOrder(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot add null object!");

    }

    @Test
    @DisplayName("Add order when order already exists")
    public void addOrderWhenExists(){

        Order orderMock = mock(Order.class);
        when(orderMock.getId()).thenReturn(0);
        when(dBdriver.getOrderById(0)).thenReturn(mock(Order.class));

        assertThatThrownBy(() -> shop.addOrder(orderMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("That order already exists!");

    }

    @Test
    @DisplayName("Add order")
    public void addOrder(){

        Order orderMock = mock(Order.class);
        when(dBdriver.getOrderById(0)).thenReturn(null);
        doNothing().when(dBdriver).addOrder(orderMock);
        shop.addOrder(orderMock);

        verify(dBdriver, times(1)).addOrder(orderMock);


    }

    @Test
    @DisplayName("Remove null order")
    public void removeNullOrder(){

        Order order = null;

        assertThatThrownBy(() -> shop.removeOrder(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot remove null object!");

    }

    @Test
    @DisplayName("Remove non existing order")
    public void removeNonExistingOrder(){

        Order orderMock = mock(Order.class);
        when(orderMock.getId()).thenReturn(0);
        when(dBdriver.getOrderById(0)).thenReturn(null);

        assertThatThrownBy(() -> shop.removeOrder(orderMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("That order doesnt exist!");

    }

    @Test
    @DisplayName("Remove order")
    public void removeOrder(){

        Order orderMock = mock(Order.class);
        when(orderMock.getId()).thenReturn(0);
        when(dBdriver.getOrderById(0)).thenReturn(mock(Order.class));
        doNothing().when(dBdriver).removeOrder(orderMock);
        shop.removeOrder(orderMock);

        verify(dBdriver, times(1)).removeOrder(orderMock);

    }

    //ciag dalszy w easymock


}
