package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import proj2.entities.Product;
import proj2.entities.ShoppingCart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class ShoppingCartTest {


    private ShoppingCart shoppingCart;

    @BeforeEach
    private void reset(){

        shoppingCart = new ShoppingCart();

    }


    @Test
    @DisplayName("Exception will be thrown when added the same product")
    public void addProductDoublesTest(){

        Product p1 = Mockito.mock(Product.class);
        Product p2 = Mockito.mock(Product.class);


        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(1);


        shoppingCart.add(p1);



        assertThatThrownBy(()->shoppingCart.add(p2)).isInstanceOf(IllegalArgumentException.class);

    }


    @Test
    @DisplayName("verifying is addProduct using products id")
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


        verify(p3, atLeast(1)).getId();

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
    @Test
    @DisplayName("When adding null Product")
    public void addNull(){

        Product p = null;

        assertThatThrownBy(()-> shoppingCart.add(p)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("remove method test")
    public void removeProductTest(){

        Product p1 = Mockito.mock(Product.class);
        Product p2 = Mockito.mock(Product.class);
        Product p3 = Mockito.mock(Product.class);

        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(2);
        when(p3.getId()).thenReturn(3);

        shoppingCart.add(p1);
        shoppingCart.add(p2);
        shoppingCart.add(p3);
        shoppingCart.remove(p1);

        assertThat(shoppingCart.getProducts().size(), is(2));

    }

    @Test
    @DisplayName("when removing non existing product in cart")
    public void removeProductException(){

        Product p = Mockito.mock(Product.class);

        assertThatThrownBy(()->shoppingCart.remove(p)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("when removing null product from cart")
    public void removeNull(){

        Product p = null;

        assertThatThrownBy(()->shoppingCart.remove(p)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("removing all products from cart")

    public void removeAll(){

        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(2);

        shoppingCart.add(p1);
        shoppingCart.add(p2);

        shoppingCart.removeAll();

        assertThat(shoppingCart.getProducts().size(), is(0));

    }



}
