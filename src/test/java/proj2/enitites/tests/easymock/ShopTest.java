package proj2.enitites.tests.easymock;

import DB.DBdriver;
import DB.Shop;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proj2.entities.Order;
import org.easymock.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.easymock.EasyMock.*;

public class ShopTest {

    @TestSubject
    private Shop shop;


    @Mock(type = MockType.NICE)
    private DBdriver dBdriver;

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

        replay(dBdriver, orderMock);

        assertThatThrownBy(() -> shop.updateOrder(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("That order doesnt exist!");

    }

    @Test
    @DisplayName("Update order")
    public void updateOrder(){

        Order orderMock = createMock(Order.class);
        expect(orderMock.getId()).andReturn(0);
        expect(dBdriver.getClientById(0)).andReturn(createMock(Order.class));
        expect(dBdriver.updateOrder(orderMock)).andVoid();

        replay(dBdriver, orderMock);

        verify();

    }

}
