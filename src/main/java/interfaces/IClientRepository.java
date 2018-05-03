package interfaces;

import models.Client;

public interface IClientRepository {
    boolean add(Client client);

    boolean update(Client client);

    boolean delete(Client client);

    Client getById(int id);

    Iterable<Client> getAll();
}
