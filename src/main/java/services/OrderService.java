package services;

import interfaces.IArticleRepository;
import interfaces.IDetailOrderRepository;
import interfaces.IOrderRepository;
import models.Article;
import models.Client;
import models.DetailOrder;
import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private IOrderRepository orderRepository;
    private IDetailOrderRepository detailOrderRepository;
    private IArticleRepository articleRepository;

    public OrderService(IOrderRepository orderRepository, IDetailOrderRepository detailOrderRepository, IArticleRepository articleRepository) {
        this.orderRepository = orderRepository;
        this.detailOrderRepository = detailOrderRepository;
        this.articleRepository = articleRepository;
    }

    public boolean addAritcleToOrder(Article article, Order order){
        if(article == null || order == null){
            throw new IllegalArgumentException("article or order is null");
        }

        if(!detailOrderRepository.getAllByArticle(article).isEmpty()){
            return false;
        }

        DetailOrder detailOrder = new DetailOrder(article.getId(), article.getId());
        return detailOrderRepository.add(detailOrder);
    }

    public boolean removeArticleFromOrder(Article article, Order order){
        if(article == null){
            throw new IllegalArgumentException("article is null");
        }

        if(order == null){
            throw new IllegalArgumentException("order is null");
        }

        if(detailOrderRepository.getAllByArticle(article).isEmpty()){
            return false;
        }

        List<DetailOrder> articlesInOrder = detailOrderRepository.getAllByOrder(order);
        for(DetailOrder detailOrder : articlesInOrder){
            if(detailOrder.getArticleId() == article.getId()){
                detailOrderRepository.delete(detailOrder);
            }
        }

        return true;
    }

    public boolean addOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("order is null");
        }

        return orderRepository.add(order);
    }

    public boolean updateOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("order is null");
        }

        return orderRepository.update(order);
    }

    public boolean deleteOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("order is null");
        }

        return orderRepository.delete(order);
    }


    public List<Order> getAllByClients(Client client){
        if(client == null){
            throw new IllegalArgumentException("order is null");
        }

        return orderRepository.getAllByClient(client);

    }
}
