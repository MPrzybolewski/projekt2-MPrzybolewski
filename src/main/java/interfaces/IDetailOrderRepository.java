package interfaces;

import models.DetailOrder;

public interface IDetailOrderRepository {
    void add(DetailOrder detailOrder);

    void update(DetailOrder detailOrder);

    void delete(DetailOrder detailOrder);

    DetailOrder getById(int id);

    Iterable<DetailOrder> getAll();

    Iterable<DetailOrder> getAllByOrder();

    Iterable<DetailOrder> getAllByObject();
}
