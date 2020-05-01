package proj2.enitites.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import proj2.entities.Client;

import static org.assertj.core.api.Assertions.assertThat;
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
    @CsvSource({"''", "null", "' '", "'    '", "A", "' Ala'", "Ma ciek", "Adam123", "Adam!",
            "Adaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaam", "adam"})
    public void unallowedNameTest(String arg){

        assertThatThrownBy(() -> client.setName(nullizier(arg))).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest(name = "{index} Surname {0} throws argument exception")
    @DisplayName("Unallowed Surname will throw exception")
    @CsvSource({"''", "null", "' '", "'    '", "A", "' Ala'", "Ma ciek", "Adam123", "Adam!",
            "Adamczyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyk", "adamczyk"})
    public void unallowedPernameTest(String arg){

        assertThatThrownBy(() -> client.setSurname(nullizier(arg))).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("Unallowed id will throw exception")
    public void unallowedIdTest(){

        assertThatThrownBy(() -> client.setId(-1)).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest(name = "{index} EmailAdress {0} throws argument exception")
    @DisplayName("Unallowed EmailAdress will throw exception")
    @CsvFileSource(resources = "/mails.csv")
    public void unallowedEmailAdressTest(String arg){

        assertThatThrownBy(() -> client.setEmailAdress(nullizier(arg))).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("toString format test")
    public void toStringTest(){

        client = new Client(10, "Patryk", "Nowak", "email@email.pl");

        assertThat(client.toString()).isEqualTo("Id: " + 10+"\tName: Patryk\tSurname: Nowak\tEmail adress: email@email.pl\n");

    }


}
