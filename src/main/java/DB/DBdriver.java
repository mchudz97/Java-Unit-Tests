package DB;

import proj2.entities.Client;

public interface DBdriver {

    void addClient(Client client);
    Client getClientById(int id);
    void removeClient(Client client);


}
