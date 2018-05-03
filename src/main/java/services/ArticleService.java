package services;

import interfaces.*;
import models.Article;

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

        return articleRepository.update(article);
    }

    public boolean deleteArticle(Article article){

        if(article == null){
            throw new IllegalArgumentException("article is null");
        }

        if(!detailOrderRepository.getAllByArticle(article).isEmpty()){
            return false;
        }

        return articleRepository.delete(article);
    }
}
