package repositories;

import interfaces.IClientRepository;
import models.Client;

import java.util.*;

public class ClientRepositoryMock implements IClientRepository {
    Map<Integer,Client> clients = new HashMap<>();

    @Override
    public void add(Client client) {
        if(clients.size() != 0){
            int lastKey = clients.get(clients.size()).getId();
            client.setId(lastKey + 1);
        } else {
            client.setId(0);
        }
        
        clients.put(client.getId(), client);
    }

    @Override
    public void update(Client client) {
        if(client == null){
            throw new NullPointerException();
        }
        
        if(!clients.containsKey(client.getId())){
            throw new IllegalArgumentException();
        }
        
        clients.remove(client.getId());
        clients.put(client.getId(), client);
    }

    @Override
    public void delete(Client client) {
        if(client == null){
            throw new NullPointerException();
        }
        clients.remove(client.getId());
            
    }

    @Override
    public Client getById(int id) {
        Client client = clients.get(id);
        return client;
    }

    @Override
    public Iterable<Client> getAll() {
        List<Client> clients =  new ArrayList<>();
        for (Client client:
             clients) {
            clients.add(client);
        }

        return clients;
    }
}
