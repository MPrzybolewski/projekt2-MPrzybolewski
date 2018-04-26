package interfaces;

import models.Client;

public interface IClientRepository {
    void add(Client client);

    void update(Client client);

    void delete(Client client);

    Client getById(int id);

    Iterable<Client> getAll();
}
