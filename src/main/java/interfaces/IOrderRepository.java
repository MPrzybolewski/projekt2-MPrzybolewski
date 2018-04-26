package interfaces;

import models.Client;
import models.Order;

public interface IOrderRepository {
    void add(Order order);

    void update(Order order);

    void delete(Order order);

    Order getById(int id);

    Iterable<Order> getAll();

    Iterable<Order> getAllByClient(Client clinet);
}
