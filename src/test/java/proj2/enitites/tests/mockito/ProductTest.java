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

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("Unallowed name will throw exception")
    @CsvSource({"''", "null", "' '", "'    '", "Ala  makota", "A", "' Ala'", "Product @$%!"})
    public void unallowedNameTest(String val){

        assertThatThrownBy(() -> {product.setName(nullizier(val));}).isInstanceOf(IllegalArgumentException.class);

    }




}
