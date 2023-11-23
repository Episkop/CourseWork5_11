package ua.student.coursetest.Model;

public class UserModel {
    String username;
    String email;

    public UserModel(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserModel() {
    }

    public static UserModel of(String email, String name) {
        return new UserModel(email, name);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
