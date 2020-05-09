package Matchers.Mockito;

import org.mockito.ArgumentMatcher;
import proj2.entities.Product;

import java.util.List;

public class ProductListMatcher implements ArgumentMatcher<List<Product>> {

    List<Product> expected;

    public ProductListMatcher(List<Product> o){

        this.expected = o;

        }

    @Override
    public boolean matches(List<Product> actual) {

        if (actual.size() == 0 && expected.size() == 0) {

        return true;

        }

        if (actual.size() != expected.size()) {

        return false;

        }

        for (Product p : expected) {

        boolean isEqual = false;

        for (Product a : actual) {


            if (a.getId() == p.getId() && a.getName() == p.getName() && a.getPrice() == p.getPrice()) {

                isEqual = true;

            }

        }

        if (!isEqual) return false;

        }

    return true;

    }

}
