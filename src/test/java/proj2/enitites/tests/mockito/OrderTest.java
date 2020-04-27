package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proj2.entities.Order;

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

}
