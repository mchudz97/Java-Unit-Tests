package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proj2.entities.Product;
import proj2.entities.ShoppingCart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

public class ShoppingCartTest {


    private ShoppingCart shoppingCart;

    @BeforeEach
    private void reset(){

        shoppingCart = new ShoppingCart();

    }


    @Test
    @DisplayName("verifying is addProduct adds to amount when ids are the same")
    public void addProductDoublesTest(){

        Product p1 = Mockito.mock(Product.class);
        Product p2 = Mockito.mock(Product.class);
        Product p3 = Mockito.mock(Product.class);

        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(1);
        when(p3.getId()).thenReturn(3);

        shoppingCart.add(p1);
        shoppingCart.add(p2);
        shoppingCart.add(p3);

        assertThat(shoppingCart.getProductAmout(p1), is(2));

    }


    @Test
    @DisplayName("verifying is addProduct method with single product amount using products id")
    public void addProductVerify(){

        Product p1 = Mockito.mock(Product.class);
        Product p2 = Mockito.mock(Product.class);
        Product p3 = Mockito.mock(Product.class);

        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(2);
        when(p3.getId()).thenReturn(3);

        shoppingCart.add(p1);
        shoppingCart.add(p2);
        shoppingCart.add(p3);

        verify(any(Product.class), atLeast(1)).getId();

    }

    @Test
    @DisplayName("addProduct method test")
    public void addProductTest(){

        Product p1 = Mockito.mock(Product.class);
        Product p2 = Mockito.mock(Product.class);
        Product p3 = Mockito.mock(Product.class);

        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(2);
        when(p3.getId()).thenReturn(3);

        shoppingCart.add(p1);
        shoppingCart.add(p2);
        shoppingCart.add(p3);

        assertThat(shoppingCart.getProducts().size(), is(3));

    }



}
