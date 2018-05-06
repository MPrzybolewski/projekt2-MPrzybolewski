import Mocks.*;
import interfaces.*;
import models.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ArticleService;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArticleServiceOwnMock {

    private IArticleRepository articleRepository;
    private IArticleValidator articleValidator;
    private ArticleService articleService;

    @BeforeEach
    void setUp(){
        articleRepository = new ArticleRepository();
    }

    @Test
    void addArticleShouldThrowWhenArticleIsNull(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysFalse();

        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        assertThrows(IllegalArgumentException.class, () -> articleService.addArticle(null), "article is null");
    }

    @Test
    void addArticleShouldReturnFalseWhenArticleIsNotValid(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysFalse();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article = new Article(-1,"test",1);

        assertFalse(articleService.addArticle(article));
    }

    @Test
    void addArticleShouldReturnTrueWhenArticleIsValid(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article = new Article(1,"test",1);

        assertTrue(articleService.addArticle(article));
    }

    @Test
    void updateArticleShouldThrowWhenArticleIsNull(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysFalse();

        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        assertThrows(IllegalArgumentException.class, () -> articleService.updateArticle(null), "article is null");
    }

    @Test
    void updateArticleShouldReturnFalseWhenArticleIsNotValid(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysFalse();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article = new Article(-1,"test",1);

        assertFalse(articleService.updateArticle(article));
    }

    @Test
    void updateArticleShouldReturnFalseWhenArticleIsNotExistInEntity(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article = new Article(1,"test",1);

        assertFalse(articleService.updateArticle(article));
    }

    @Test
    void updateArticleShouldReturnTrueWhenArticleIsValidAndExistInEntity(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article = new Article(1,"test",1);

        articleService.addArticle(article);

        assertTrue(articleService.updateArticle(article));
    }

    @Test
    void deleteArticleShouldThrowWhenArticleIsNull(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysFalse();

        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        assertThrows(IllegalArgumentException.class, () -> articleService.deleteArticle(null), "article is null");
    }

    @Test
    void deleteArticleShouldReturnFalseWhenArticleIsNotExistInEntity(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article = new Article(1,"test",1);

        assertFalse(articleService.deleteArticle(article));
    }

    @Test
    void deleteArticleShouldReturnFalseWhenArticleHasOrders(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnNotEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);
        Article article = new Article(1,"test",1);

        articleService.addArticle(article);

        assertFalse(articleService.deleteArticle(article));
    }

    @Test
    void deleteArticleShouldReturnTrueWhenArticleIsValidAndExistInEntity(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);
        Article article = new Article(1,"test",1);

        articleService.addArticle(article);

        assertTrue(articleService.deleteArticle(article));
    }

    @Test
    void getAllArticlesShouldReturnEmptyListWhenEntityisEmpty(){
        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        assertEquals(articleRepository.getAll().size(), 0);
    }

    @Test
    void getAllArticleShouldReturnCorrectListWhenEntityIsNotEmpty(){

        IDetailOrderRepository detailOrderRepository = new DetailOrderRepositoryReturnEmptyList();
        IArticleValidator articleValidator = new ArticleValidatorReturnAlwaysTrue();
        ArticleService articleService = new ArticleService(articleRepository,detailOrderRepository,articleValidator);

        Article article1 = new Article(1,"test",1);
        Article article2 = new Article(2,"test",1);
        Article article3 = new Article(3,"test",1);

        articleService.addArticle(article1);
        articleService.addArticle(article2);
        articleService.addArticle(article3);

        assertEquals(articleRepository.getAll().size(), 3);
    }


}
