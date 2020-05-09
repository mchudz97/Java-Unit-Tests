package proj2.enitites.tests.mockito;

import DB.DBdriver;
import DB.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import proj2.entities.Client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class ShopTest {

    private DBdriver dBdriver;

    @InjectMocks
    private Shop shop;

    private void setupDbMock(){

        dBdriver = mock(DBdriver.class);


    }

    @BeforeEach
    private void reset(){

        dBdriver = mock(DBdriver.class);
        shop = new Shop(dBdriver);

    }

    @Test
    @DisplayName("Shop initialization test")
    public void initTest(){


        assertThat(shop.getDBdriver(), is(dBdriver));

    }

    @Test
    @DisplayName("Add Client if client with the same id exists")
    public void addWhenExists(){

        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(0);
        when(dBdriver.getClientById(0)).thenReturn(mock(Client.class));

        assertThatThrownBy(() -> shop.addClient(clientMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client with that id already exists!");

    }

    @Test
    @DisplayName("Add Client if client is null")
    public void addWhenNull(){

        Client client = null;

        assertThatThrownBy(() -> shop.addClient(client))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot add null object");

    }

    @Test
    @DisplayName("Add Client")
    public void addClient(){

        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(0);
        when(dBdriver.getClientById(0)).thenReturn(null);

        final boolean[] isAdded = {false};

        when(dBdriver.addClient(clientMock)).thenAnswer(new Answer<Void>(){


            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                isAdded[0] = true;
                return null;
            }

        });

        assertThat(isAdded[0], is(true));

    }


}
