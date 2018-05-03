package services;

import interfaces.IArticleRepository;
import interfaces.IDetailOrderRepository;
import interfaces.IOrderRepository;
import models.Article;
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
            return false;
        }

        if(!detailOrderRepository.getAllByArticle(article).isEmpty()){
            return false;
        }

        DetailOrder detailOrder = new DetailOrder(article.getId(), article.getId());
        return detailOrderRepository.add(detailOrder);
    }

    public boolean removeArticleFromOrder(Article article, Order order){
        if(article == null || order == null){
            return false;
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

    public double getTotalPrice(Order order){
        if(order == null){
            throw new IllegalArgumentException("order is null");
        }
        List<DetailOrder> allDetailOrders = detailOrderRepository.getAllByOrder(order);
        List<Article> allArticles = new ArrayList<>();

        for(DetailOrder detailOrder : allDetailOrders){
            allArticles.add(articleRepository.getById(detailOrder.getArticleId()));
        }

        double totalPrice = 0;
        for(Article article : allArticles){
            totalPrice += article.getPrice();
        }

        return  totalPrice;
    }
}
