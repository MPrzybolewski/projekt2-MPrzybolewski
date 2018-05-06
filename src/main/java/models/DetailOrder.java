package models;

public class DetailOrder {
    int articleId;
    int orderId;

    public DetailOrder(int articleId, int orderId) {
        this.articleId = articleId;
        this.orderId = orderId;
    }

    public int getArticleId() {
        return articleId;
    }

}
