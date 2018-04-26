package interfaces;

import models.Article;

public interface IArticleRepository {
    void add(Article object);

    void update(Article object);

    void delete(Article object);

    Article getById(int id);

    Iterable<Article> getAll();
}
