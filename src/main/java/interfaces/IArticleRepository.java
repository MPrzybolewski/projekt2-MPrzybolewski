package interfaces;

import models.Article;

import java.util.List;

public interface IArticleRepository {
    boolean add(Article object);

    boolean update(Article object);

    boolean delete(Article object);

    Article getById(int id);

    List<Article> getAll();
}
