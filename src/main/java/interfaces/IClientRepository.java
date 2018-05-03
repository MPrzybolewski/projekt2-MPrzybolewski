package interfaces;

import models.Client;

import java.util.List;

public interface IClientRepository {
    boolean add(Client client);

    boolean update(Client client);

    boolean delete(Client client);

    Client getById(int id);

    List<Client> getAll();
}
