package Mocks;

import interfaces.IArticleRepository;
import models.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepository implements IArticleRepository {
    Map<Integer, Article> articles = new HashMap<>();

    @Override
    public boolean add(Article article) {
        if(articles.size() != 0){
            int lastKey = articles.get(articles.size()-1).getId();
            article.setId(lastKey + 1);
        } else {
            article.setId(0);
        }

        articles.put(article.getId(), article);
        return true;
    }

    @Override
    public boolean update(Article article) {
        if(article == null){
            throw new NullPointerException();
        }

        if(!articles.containsKey(article.getId())){
            throw new IllegalArgumentException();
        }

        articles.remove(article.getId());
        articles.put(article.getId(), article);
        return true;
    }

    @Override
    public boolean delete(Article article) {
        if(article == null){
            throw new NullPointerException();
        }

        articles.remove(article.getId());
        return true;
    }

    @Override
    public Article getById(int id) {
        return  articles.get(id);
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(articles.values());
    }
}
