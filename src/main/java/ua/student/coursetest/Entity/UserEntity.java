package ua.student.coursetest.Entity;

import ua.student.coursetest.Model.UserModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    //    private String password;
//    @Enumerated(value = EnumType.STRING)
//    private Role role;
    private String email;
    @OneToMany(mappedBy = "userProfit", cascade = CascadeType.ALL)
    private List<ProfitEntity> profits = new ArrayList<>();

    @OneToMany(mappedBy = "userSpending", cascade = CascadeType.ALL)
    private List<SpendingEntity> spendings = new ArrayList<>();

    @OneToOne(mappedBy = "userProfitTotal", cascade = CascadeType.ALL)
    private ProfitTotalEntity totalProf = new ProfitTotalEntity();

    @OneToOne(mappedBy = "userSpendingTotal", cascade = CascadeType.ALL)
    private SpendingTotalEntity totalSpend = new SpendingTotalEntity();


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<ProfitEntity> profits = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
//    private List<SpendingEntity> spending = new ArrayList<>();


    public UserEntity(String email, String name) {
        this.name = name;
        this.email = email;
    }

    public UserEntity() {
    }

    public static UserEntity of(String email, String username) {
        return new UserEntity(email, username);
    }

    public void addProfit(ProfitEntity profitEntity) {
        profitEntity.setUserProfit(this);
        profits.add(profitEntity);
    }

    public void addSpending(SpendingEntity spendingEntity) {
        spendingEntity.setUserSpending(this);
        spendings.add(spendingEntity);
    }

    public void addProfitTotal(ProfitTotalEntity profitTotalEntity) {
        profitTotalEntity.setUserProfitTotal(this);
        totalProf = profitTotalEntity;
    }

    public void addSpendingTotal(SpendingTotalEntity spendingTotalEntity) {
        spendingTotalEntity.setUserSpendingTotal(this);
        totalSpend = spendingTotalEntity;
    }

    public UserModel toModel() {
        return UserModel.of(email, name);
    }

    public static UserEntity fromModel(UserModel userModel) {
        return UserEntity.of(userModel.getEmail(),userModel.getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProfitEntity> getProfits() {
        return profits;
    }

    public void setProfits(List<ProfitEntity> profits) {
        this.profits = profits;
    }

    public List<SpendingEntity> getSpendings() {
        return spendings;
    }

    public void setSpendings(List<SpendingEntity> spendings) {
        this.spendings = spendings;
    }

    public ProfitTotalEntity getTotalProf() {
        return totalProf;
    }

    public void setTotalProf(ProfitTotalEntity totalProf) {
        this.totalProf = totalProf;
    }

    public SpendingTotalEntity getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(SpendingTotalEntity totalSpend) {
        this.totalSpend = totalSpend;
    }
}
