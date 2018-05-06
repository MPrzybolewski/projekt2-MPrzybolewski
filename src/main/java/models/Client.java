package models;

public class Client {
    int id;
    String firstName;
    String secondName;
    String email;

    public Client(int id, String firstName, String secondName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }


    public int getId() {
        return id;
    }
}
