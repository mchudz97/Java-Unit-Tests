package proj2.enitites.tests.easymock;

import DB.DBdriver;
import DB.Shop;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proj2.entities.Order;
import org.easymock.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.easymock.EasyMock.*;

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

}