package models;

public class detailOrder {
    int objectId;
    int orderId;

    public detailOrder(int objectId, int orderId) {
        this.objectId = objectId;
        this.orderId = orderId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
