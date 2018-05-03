package interfaces;

import models.Article;

public interface IArticleRepository {
    boolean add(Article object);

    boolean update(Article object);

    boolean delete(Article object);

    Article getById(int id);

    Iterable<Article> getAll();
}
