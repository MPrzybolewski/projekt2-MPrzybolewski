package interfaces;

import models.Article;
import models.DetailOrder;
import models.Order;

public interface IDetailOrderRepository {
    void add(DetailOrder detailOrder);

    void update(DetailOrder detailOrder);

    void delete(DetailOrder detailOrder);

    DetailOrder getById(int id);

    Iterable<DetailOrder> getAll();

    Iterable<DetailOrder> getAllByOrder(Order order);

    Iterable<DetailOrder> getAllByArticle(Article article);
}
