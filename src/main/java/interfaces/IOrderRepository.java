package interfaces;

import models.Client;
import models.Order;

public interface IOrderRepository {
    boolean add(Order order);

    boolean update(Order order);

    boolean delete(Order order);

    Order getById(int id);

    Iterable<Order> getAll();

    Iterable<Order> getAllByClient(Client clinet);
}
