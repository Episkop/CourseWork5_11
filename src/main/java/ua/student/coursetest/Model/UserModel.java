package ua.student.coursetest.Model;

public class UserModel {
    String name;
    String email;

    public UserModel(String email,String name) {
        this.name = name;
        this.email = email;
    }

    public UserModel() {
    }

    public static UserModel of(String email,String username) {
        return new UserModel(email,username);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
