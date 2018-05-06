import interfaces.IClientRepository;
import interfaces.IClientValidator;
import interfaces.IOrderRepository;
import models.Client;
import models.Order;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ClientService;
import services.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientServiceEasyMockTest {

    private IClientRepository clientRepository;
    private IClientValidator clientValidator;
    private IOrderRepository orderRepository;
    private ClientService clientService;

    @BeforeEach
    void setUp(){
        clientRepository = EasyMock.createMock(IClientRepository.class);
        clientValidator = EasyMock.createMock(IClientValidator.class);
        orderRepository = EasyMock.createMock(IOrderRepository.class);
        clientService = new ClientService(clientRepository, orderRepository, clientValidator);
    }

    @Test
    void addClientShouldThrowWhenClientIsNull(){
        assertThrows(IllegalArgumentException.class, () -> clientService.addClient(null), "client is null");
    }

    @Test
    void addClientShouldReturnFalseWhenClientIsNotValid(){
        Client client = new Client(-1,"test","test","test");
        expect(clientValidator.isClientValid(client)).andReturn(false);
        replay(clientValidator);

        assertFalse(clientService.addClient(client));
    }

    @Test
    void addClientShouldReturnTrueWhenClientIsValid(){
        Client client = new Client(1,"test","test","test");
        expect(clientValidator.isClientValid(client)).andReturn(true);
        replay(clientValidator);

        assertFalse(clientService.addClient(client));
    }

    @Test
    void updateClientShouldThrowWhenClientIsNull(){
        assertThrows(IllegalArgumentException.class, () -> clientService.updateClient(null), "client is null");
    }

    @Test
    void updateClientShouldReturnFalseWhenClientIsNotValid(){
        Client client = new Client(-1,"test","test","test");
        expect(clientValidator.isClientValid(client)).andReturn(false);
        replay(clientValidator);

        assertFalse(clientService.updateClient(client));
    }

    @Test
    void updateClientShouldReturnFalseWhenClientIsNotExistInEntity(){
        Client client = new Client(2,"test","test","test");

        expect(clientValidator.isClientValid(client)).andReturn(true);
        replay(clientValidator);

        expect(clientRepository.getById(2)).andReturn(null);
        replay(clientRepository);

        assertFalse(clientService.updateClient(client));
    }

    @Test
    void updateClientShouldReturnTrueWhenClientIsValidAndExistInEntity(){
        Client client = new Client(2,"test","test","test");

        expect(clientValidator.isClientValid(client)).andReturn(true);
        replay(clientValidator);

        expect(clientRepository.getById(2)).andReturn(client);
        expect(clientRepository.update(client)).andReturn(true);
        replay(clientRepository);

        assertTrue(clientService.updateClient(client));
    }

    @Test
    void deleteClientShouldThrowWhenClientIsNull(){
        assertThrows(IllegalArgumentException.class, () -> clientService.deleteClient(null), "client is null");
    }

    @Test
    void deleteClientShouldReturnFalseWhenClientIsNotExistInEntity(){
        Client client = new Client(2,"test","test","test");

        expect(clientValidator.isClientValid(client)).andReturn(true);
        replay(clientValidator);

        expect(clientRepository.getById(2)).andReturn(null);
        replay(clientRepository);

        assertFalse(clientService.deleteClient(client));
    }

    @Test
    void deleteClientShouldReturnFalseWhenClientHasOrders(){
        Client client = new Client(2,"test","test","test");
        Order order = new Order(1,2);
        List<Order> orders = new ArrayList<>();

        orders.add(order);

        expect(clientValidator.isClientValid(client)).andReturn(true);
        replay(clientValidator);

        expect(clientRepository.getById(2)).andReturn(client);
        expect(clientRepository.delete(client)).andReturn(true);
        replay(clientRepository);

        expect(orderRepository.getAllByClient(client)).andReturn(orders);
        replay(orderRepository);

        assertFalse(clientService.deleteClient(client));
    }

    @Test
    void deleteClientShouldReturnTrueWhenClientIsValidAndExistInEntity(){
        Client client = new Client(2,"test","test","test");

        expect(clientValidator.isClientValid(client)).andReturn(true);
        replay(clientValidator);

        expect(clientRepository.getById(2)).andReturn(client);
        expect(clientRepository.delete(client)).andReturn(true);
        replay(clientRepository);

        expect(orderRepository.getAllByClient(client)).andReturn(new ArrayList<>());
        replay(orderRepository);

        assertTrue(clientService.deleteClient(client));
    }

    @Test
    void getAllClientsShouldReturnEmptyListWhenEntityisEmpty(){
        expect(clientRepository.getAll()).andReturn(new ArrayList<>());
        replay(clientRepository);

        assertEquals(clientRepository.getAll().size(), 0);
    }

    @Test
    void getAllClientShouldReturnCorrectListWhenEntityIsNotEmpty(){
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(1,"1","1","1");
        Client client2 = new Client(1,"1","1","1");
        Client client3 = new Client(1,"1","1","1");

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);

        expect(clientRepository.getAll()).andReturn(clients);
        replay(clientRepository);

        assertEquals(clientRepository.getAll().size(), 3);
    }
}
