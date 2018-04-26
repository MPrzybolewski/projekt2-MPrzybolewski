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

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
