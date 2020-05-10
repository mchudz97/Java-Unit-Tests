package proj2.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proj2.entities.Order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTest {

    private Order order;


    @BeforeEach
    private void reset(){

        this.order = new Order();

    }

    @Test
    @DisplayName("When negative Id value")
    public void unallowedIdValue(){

        assertThatThrownBy(() -> order.setId(-1)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("When negative clientId value")
    public void unallowedClientIdValue(){

        assertThatThrownBy(() -> order.setClientId(-1)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("toString format test")
    public void toStringTest(){

        order = new Order(10, 15);

        assertThat(order.toString()).isEqualTo("Id: 10\tClient id: 15\n");

    }

}
