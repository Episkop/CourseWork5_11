package ua.student.coursetest.Model;

public class UserModel {
    String name;
    String email;
    private String pictureUrl;

    public UserModel(String email,String name,String pictureUrl) {
        this.pictureUrl = pictureUrl;
        this.name = name;
        this.email = email;
    }

    public UserModel() {
    }

    public static UserModel of(String email,String username,String pictureUrl) {
        return new UserModel(email,username,pictureUrl);
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
