package Matchers.Mockito;
import org.mockito.ArgumentMatcher;
import proj2.entities.Product;

public class ProductMatcher implements ArgumentMatcher<Product> {

    private Product expected;

    public ProductMatcher(Product p){

        this.expected = p;

    }


    @Override
    public boolean matches(Product product) {

        return  expected.getId() == product.getId()
                && expected.getPrice() == product.getPrice()
                && expected.getName() == product.getName();

    }

}
