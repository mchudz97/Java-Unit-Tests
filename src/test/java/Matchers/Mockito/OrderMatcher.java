package Matchers.Mockito;

import org.mockito.ArgumentMatcher;
import proj2.entities.Order;


public class OrderMatcher implements ArgumentMatcher<Order> {

    private Order expected;

    public OrderMatcher(Order o){

        this.expected = o;

    }


    @Override
    public boolean matches(Order order) {

        return  expected.getId() == order.getId()
                && expected.getClientId() == order.getClientId();

    }


}
