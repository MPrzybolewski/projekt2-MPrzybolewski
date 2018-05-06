package Mocks;

import interfaces.IArticleValidator;
import interfaces.IClientValidator;
import models.Article;
import models.Client;

public class ArticleValidatorReturnAlwaysTrue implements IArticleValidator {

    @Override
    public boolean isArticleValid(Article article) {
        return true;
    }
}
