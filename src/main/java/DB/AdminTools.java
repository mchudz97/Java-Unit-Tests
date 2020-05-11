package DB;

import proj2.entities.Client;

import java.util.Date;
import java.util.List;

public interface AdminTools {

    void sendMessage(String sender, Client receiver, String description);
    void sendBroadcastMessage(String sender, List<Client> clients, String description);
    boolean checkIsActive(String email);

}
