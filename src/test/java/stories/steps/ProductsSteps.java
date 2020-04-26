package stories.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import proj2.entities.Product;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductsSteps {


    private Product product;
    private String name;

    @Given("a Product")
    public void givenProduct(){

        product = new Product();

    }

    @When("create name with invalid $value")
    public void nameProduct(String value){

        name = name;

    }

    @Then("exception should be thrown")
    public void nameExceptionThrown(){

        assertThatThrownBy(() -> product.setName(name) ).isInstanceOf(IllegalArgumentException.class);

    }


}
