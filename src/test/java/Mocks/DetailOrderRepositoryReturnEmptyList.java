package Mocks;

import interfaces.IDetailOrderRepository;
import models.Article;
import models.DetailOrder;
import models.Order;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderRepositoryReturnEmptyList implements IDetailOrderRepository {
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
        return new ArrayList<>();
    }
}
