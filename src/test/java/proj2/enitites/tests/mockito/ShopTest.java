package proj2.enitites.tests.mockito;

import DB.DBdriver;
import DB.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mock.*;

public class ShopTest {

    private DBdriver dBdriver;

    @InjectMocks
    private Shop shop;



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



}
