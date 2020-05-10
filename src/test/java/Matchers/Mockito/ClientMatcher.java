package Matchers.Mockito;

import org.mockito.ArgumentMatcher;
import proj2.entities.Client;

public class ClientMatcher implements ArgumentMatcher<Client> {

    private Client expected;

    public ClientMatcher(Client p){

        this.expected = p;

    }


    @Override
    public boolean matches(Client client) {

        return  expected.getId() == client.getId()
                && expected.getEmailAdress() == client.getEmailAdress()
                && expected.getName() == client.getName() && expected.getSurname() == client.getSurname();

    }
}
