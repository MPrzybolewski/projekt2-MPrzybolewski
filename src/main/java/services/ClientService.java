package services;

import interfaces.IClientRepository;
import interfaces.IClientValidator;
import interfaces.IOrderRepository;
import models.Client;

public class ClientService {
    private IClientRepository clientRepository;
    private IOrderRepository orderRepository;
    private IClientValidator clientValidator;

    public ClientService(IClientRepository clientRepository, IOrderRepository orderRepository, IClientValidator clientValidator) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.clientValidator = clientValidator;
    }

    public boolean addClient(Client client){

        if(!ifOperationCanBeExecuted(client)){
            return false;
        }

        return clientRepository.add(client);
    }

    public boolean updateClient(Client client){

        if(!ifOperationCanBeExecuted(client)){
            return false;
        }

        if(!clientValidator.isClientValid(client)){
            return false;
        }

        return clientRepository.update(client);
    }

    public boolean deleteClient(Client client){

        if(!ifOperationCanBeExecuted(client)){
            return false;
        }

        if(!clientValidator.isClientValid(client)){
            return false;
        }

        return clientRepository.delete(client);
    }

    private boolean ifOperationCanBeExecuted(Client client){
        if(client == null){
            throw new IllegalArgumentException("client is null");
        }

        if(!clientValidator.isClientValid(client)){
            return false;
        }

        return true;
    }





}
