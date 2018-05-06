package services;

import interfaces.*;
import models.Article;
import models.Client;
import models.Order;

import java.util.List;

public class ArticleService {
    private IArticleRepository articleRepository;
    private IDetailOrderRepository detailOrderRepository;
    private IArticleValidator articleValidator;

    public ArticleService(IArticleRepository articleRepository, IDetailOrderRepository detailOrderRepository, IArticleValidator articleValidator) {
        this.articleRepository = articleRepository;
        this.detailOrderRepository = detailOrderRepository;
        this.articleValidator = articleValidator;
    }

    public boolean addArticle(Article article){

        if(article == null){
            throw new IllegalArgumentException("article is null");
        }

        if(!articleValidator.isArticleValid(article)){
            return false;
        }

        return articleRepository.add(article);
    }

    public boolean updateArticle(Article article){

        if(article == null){
            throw new IllegalArgumentException("article is null");
        }

        if(!articleValidator.isArticleValid(article)){
            return false;
        }

        if(articleRepository.getById(article.getId()) == null){
            return false;
        }

        return articleRepository.update(article);
    }

    public boolean deleteArticle(Article article){

        if(article == null){
            throw new IllegalArgumentException("article is null");
        }

        if(articleRepository.getById(article.getId()) == null){
            return false;
        }

        if(!detailOrderRepository.getAllByArticle(article).isEmpty()){
            return false;
        }

        return articleRepository.delete(article);
    }


}
