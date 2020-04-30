package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import proj2.entities.Product;

import static org.assertj.core.api.Assertions.*;


public class ProductTest {

    private Product product;



    @BeforeEach
    public void reset(){

        product = new Product();

    }

    private String nullizier(String nullexample){

        if(nullexample == "null" || nullexample == "Null"){

            return null;

        }

        return nullexample;


    }

    @ParameterizedTest(name = "{index} Name {0} throws argument exception")
    @DisplayName("Unallowed name will throw exception")
    @CsvSource({"''", "null", "' '", "'    '", "Ala  makota", "A", "' Ala'", "Product @$%!", "ala"})
    public void unallowedNameTest(String val){

        assertThatThrownBy(() -> product.setName(nullizier(val))).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("Unallowed id will throw exception")
    public void unallowedIdTest(){

        assertThatThrownBy(() -> product.setId(-1)).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest(name = "{index} Price {0} throws argument exception")
    @DisplayName("Unallowed price will throw exception")
    @CsvSource({"0", "-1"})
    public void unallowedPriceTest(String arg){

        assertThatThrownBy(() -> product.setPrice(Float.parseFloat(arg))).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("toString format test")
    public void toStringTest(){

        product.setId(10);
        product.setName("Product");
        product.setPrice(20.0f);

        assertThat(product.toString()).isEqualTo("Id: 10\tName: Product\tPrice: 20.0");

    }




}
