package repositories;

import interfaces.IArticleRepository;
import interfaces.IClientRepository;
import models.Article;
import models.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepositoryMock implements IArticleRepository {
    Map<Integer, Article> articles = new HashMap<>();

    @Override
    public void add(Article article) {
        if(articles.size() != 0){
            int lastKey = articles.get(articles.size()).getId();
            article.setId(lastKey + 1);
        } else {
            article.setId(0);
        }

        articles.put(article.getId(), article);
    }

    @Override
    public void update(Article article) {
        if(article == null){
            throw new NullPointerException();
        }

        if(!articles.containsKey(article.getId())){
            throw new IllegalArgumentException();
        }

        articles.remove(article.getId());
        articles.put(article.getId(), article);
    }

    @Override
    public void delete(Article article) {
        if(article == null){
            throw new NullPointerException();
        }
        articles.remove(article.getId());

    }

    @Override
    public Article getById(int id) {
        return  articles.get(id);
    }

    @Override
    public Iterable<Article> getAll() {
        return new ArrayList<>(articles.values());
    }
}