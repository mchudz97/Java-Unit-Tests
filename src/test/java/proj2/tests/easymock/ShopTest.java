package proj2.tests.easymock;

import DB.DBdriver;
import DB.Shop;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proj2.entities.Client;
import proj2.entities.Order;
import org.easymock.*;

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

        dBdriver = createMock(MockType.NICE, DBdriver.class);
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
        expect(dBdriver.getClientById(0)).andReturn(null);

        replay(orderMock);
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

}
