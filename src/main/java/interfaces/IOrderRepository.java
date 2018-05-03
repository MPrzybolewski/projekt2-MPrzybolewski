package interfaces;

import models.Client;
import models.Order;

import java.util.List;

public interface IOrderRepository {
    boolean add(Order order);

    boolean update(Order order);

    boolean delete(Order order);

    Order getById(int id);

    List<Order> getAll();

    List<Order> getAllByClient(Client clinet);
}
