package interfaces;

import models.Article;
import models.DetailOrder;
import models.Order;

import java.util.List;

public interface IDetailOrderRepository {
    boolean add(DetailOrder detailOrder);

    boolean update(DetailOrder detailOrder);

    boolean delete(DetailOrder detailOrder);

    DetailOrder getById(int id);

    List<DetailOrder> getAll();

    List<DetailOrder> getAllByOrder(Order order);

    List<DetailOrder> getAllByArticle(Article article);
}
