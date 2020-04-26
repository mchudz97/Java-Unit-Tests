package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import proj2.entities.Order;

public class OrderTest {

    private Order order;


    @BeforeEach
    private void reset(){

        this.order = new Order();

    }

}
