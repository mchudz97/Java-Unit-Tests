package Matchers.Mockito;

import org.mockito.ArgumentMatcher;
import proj2.entities.Client;
import proj2.entities.Order;
import proj2.entities.Product;

import java.util.List;

public class ClientListMatcher implements ArgumentMatcher<List<Client>> {

    List<Client> expected;

    public ClientListMatcher(List<Client> o){

        this.expected = o;

    }

    @Override
    public boolean matches(List<Client> actual) {

        if (actual.size() == 0 && expected.size() == 0) {

            return true;

        }

        if (actual.size() != expected.size()) {

            return false;

        }

        for (Client c : expected) {

            boolean isEqual = false;

            for (Client a : actual) {


                if (a.getId() == c.getId() && a.getName() == c.getName()
                        && a.getEmailAdress() == c.getEmailAdress()
                        && a.getSurname() == c.getSurname()) {

                    isEqual = true;

                }

            }

            if (!isEqual) return false;

        }

        return true;

    }
}
