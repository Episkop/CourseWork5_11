package ua.student.coursetest.Entity;

import jakarta.persistence.*;
import ua.student.coursetest.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;

//    private String password;
//    @Enumerated(value = EnumType.STRING)
//    private Role role;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProfitEntity> profits = new ArrayList<>();

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    private List<SpendingEntity> spending = new ArrayList<>();


    public UserEntity(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserEntity() {
    }

    public static UserEntity of(String username, String email) {
        return new UserEntity(username,email);
    }

    public void addProfitEntity(ProfitEntity profitEntity) {
        profitEntity.setUser(this);
        profits.add(profitEntity);
    }

    public void addSpendingEntity(SpendingEntity spendingEntity) {
        spendingEntity.setUser1(this);
        spending.add(spendingEntity);
    }

    public UserModel toModel() {
        return UserModel.of(username,email);
    }

    public static UserEntity fromModel(UserModel userModel) {
        return UserEntity.of(userModel.getEmail(), userModel.getUsername());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProfitEntity> getProfits() {
        return profits;
    }

    public void setProfits(List<ProfitEntity> profits) {
        this.profits = profits;
    }

    public List<SpendingEntity> getSpending() {
        return spending;
    }

    public void setSpending(List<SpendingEntity> spending) {
        this.spending = spending;
    }

}
