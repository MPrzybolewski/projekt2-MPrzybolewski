package interfaces;

import models.Article;
import models.Client;

public interface IArticleValidator {
    boolean isArticleValid(Article article);
}
