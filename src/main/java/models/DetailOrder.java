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

    public void setArticleId(int objectId) {
        this.articleId = objectId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
