package models;

public class Order {
    int id;
    int clientId;

    public Order(int id, int clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

}
