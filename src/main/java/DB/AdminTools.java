package DB;

import proj2.entities.Client;

import java.util.Date;
import java.util.List;

public interface AdminTools {

    void sendMessage(String sender, Client receiver);
    void sendBroadcastMessage(String sender, List<Client> clients);
    boolean checkIsActive(String email);

}
