package Mocks;

import interfaces.IDetailOrderRepository;
import models.Article;
import models.DetailOrder;
import models.Order;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderRepositoryReturnNotEmptyList implements IDetailOrderRepository {
    @Override
    public boolean add(DetailOrder detailOrder) {
        return false;
    }

    @Override
    public boolean update(DetailOrder detailOrder) {
        return false;
    }

    @Override
    public boolean delete(DetailOrder detailOrder) {
        return false;
    }

    @Override
    public List<DetailOrder> getAll() {
        return null;
    }

    @Override
    public List<DetailOrder> getAllByOrder(Order order) {
        return null;
    }

    @Override
    public List<DetailOrder> getAllByArticle(Article article) {
        DetailOrder detailOrder1 = new DetailOrder(article.getId(), 1);
        DetailOrder detailOrder2 = new DetailOrder(article.getId(), 2);
        DetailOrder detailOrder3 = new DetailOrder(article.getId(), 3);

        List<DetailOrder> detailOrders = new ArrayList<>();
        detailOrders.add(detailOrder1);
        detailOrders.add(detailOrder2);
        detailOrders.add(detailOrder3);

        return detailOrders;
    }
}
