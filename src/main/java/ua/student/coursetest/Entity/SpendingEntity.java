package ua.student.coursetest.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import ua.student.coursetest.Model.SpendingModel;

@Entity
@Getter
@Table(name = "spending")
public class SpendingEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id",nullable = false)
//    private Long id;
    @Column(name = "article",nullable = false)
    private String article;
    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;
    private Double year;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user1;


    public SpendingEntity(String article, Double january, Double february, Double march, Double april, Double may,
                          Double june, Double july, Double august, Double september, Double october, Double november,
                          Double december, Double year) {
        this.article = article;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
        this.year = year;
    }
    public static SpendingEntity of(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year){
        return new SpendingEntity(article, january, february, march, april, may, june, july, august,
                september, october, november, december,year);
    }

    public SpendingModel toModel() {
        return SpendingModel.of(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }

    public static SpendingEntity fromModel (SpendingModel model){
        return SpendingEntity.of(model.getArticle(),model.getJanuary(),model.getFebruary(),model.getMarch(),model.getApril(),
                model.getMay(), model.getJune(),model.getJuly(), model.getAugust(), model.getSeptember(), model.getOctober(),
                model.getNovember(), model.getDecember(), model.getYear());
    }
    public SpendingEntity() {
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public void setUser1(UserEntity user1) {
        this.user1 = user1;
    }
}
