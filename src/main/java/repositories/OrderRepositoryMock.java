package repositories;

import interfaces.IOrderRepository;
import models.Client;
import models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRepositoryMock implements IOrderRepository {
    Map<Integer,Order> orders = new HashMap<>();

    @Override
    public void add(Order order) {
        if(orders.size() != 0){
            int lastKey = orders.get(orders.size()).getId();
            order.setId(lastKey + 1);
        } else {
            order.setId(0);
        }

        orders.put(order.getId(), order);
    }

    @Override
    public void update(Order order) {
        if(order == null){
            throw new NullPointerException();
        }

        if(!orders.containsKey(order.getId())){
            throw new IllegalArgumentException();
        }

        orders.remove(order.getId());
        orders.put(order.getId(), order);
    }

    @Override
    public void delete(Order order) {
        if(order == null){
            throw new NullPointerException();
        }
        orders.remove(order.getId());
    }

    @Override
    public Order getById(int id) {
        return orders.get(id);
    }

    @Override
    public Iterable<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Iterable<Order> getAllByClient(Client client) {
        return new ArrayList<>(orders.values().stream().
        filter((tempClient) -> tempClient.getId() == client.getId()).collect(Collectors.toList()));
    }

}

