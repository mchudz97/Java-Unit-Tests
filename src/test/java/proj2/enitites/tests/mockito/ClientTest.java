package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import proj2.entities.Client;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClientTest {

    private Client client;



    private String nullizier(String nullexample){

        if(nullexample == "null" || nullexample == "Null"){

            return null;

        }

        return nullexample;

    }

    @BeforeEach
    public void reset(){

        client = new Client();

    }

    @ParameterizedTest(name = "{index} Name {0} throws argument exception")
    @DisplayName("Unallowed name will throw exception")
    @CsvSource({"''", "null", "' '", "'    '", "A", "' Ala'", "Ma ciek", "Adam123", "Adam!"})
    public void unallowedNameTest(String arg){

        assertThatThrownBy(() -> client.setName(arg)).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest(name = "{index} Surname {0} throws argument exception")
    @DisplayName("Unallowed Surname will throw exception")
    @CsvSource({"''", "null", "' '", "'    '", "A", "' Ala'", "Ma ciek", "Adam123", "Adam!"})
    public void unallowedPernameTest(String arg){

        assertThatThrownBy(() -> client.setSurname(arg)).isInstanceOf(IllegalArgumentException.class);

    }


}
