package ua.student.coursetest.Entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.Hibernate;
import ua.student.coursetest.Model.ProfitModel;

import java.util.Objects;

@Entity
@Setter
@Table (name = "profit")
public class ProfitEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
    @Column(name = "article", nullable = false)
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public ProfitEntity(String article, Double january, Double february, Double march, Double april, Double may,
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

    public ProfitEntity() {
    }

    public static ProfitEntity of(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year){
        return new ProfitEntity(article, january, february, march, april, may, june, july, august,
                september, october, november, december,year);
    }

//    public static ProfitEntity fromModel(StartUpModel startUpModel) {
//        return ProfitEntity.of(startUpModel.getArticle(),startUpModel.getJanuary(),startUpModel.getFebruary(),startUpModel.getMarch(),startUpModel.getApril(),
//                startUpModel.getMay(), startUpModel.getJune(),startUpModel.getJuly(), startUpModel.getAugust(), startUpModel.getSeptember(), startUpModel.getOctober(),
//                startUpModel.getNovember(), startUpModel.getDecember(), startUpModel.getYear());
//    }

    public ProfitModel toModel() {
        return ProfitModel.of(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }

    public static ProfitEntity fromModel (ProfitModel model){
        return ProfitEntity.of(model.getArticle(),model.getJanuary(),model.getFebruary(),model.getMarch(),model.getApril(),
                model.getMay(), model.getJune(),model.getJuly(), model.getAugust(), model.getSeptember(), model.getOctober(),
                model.getNovember(), model.getDecember(), model.getYear());
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    public Long getId() {
//        return this.id;
//    }

    public String getArticle() {
        return this.article;
    }

    public Double getJanuary() {
        return this.january;
    }

    public Double getFebruary() {
        return this.february;
    }

    public Double getMarch() {
        return this.march;
    }

    public Double getApril() {
        return this.april;
    }

    public Double getMay() {
        return this.may;
    }

    public Double getJune() {
        return this.june;
    }

    public Double getJuly() {
        return this.july;
    }

    public Double getAugust() {
        return this.august;
    }

    public Double getSeptember() {
        return this.september;
    }

    public Double getOctober() {
        return this.october;
    }

    public Double getNovember() {
        return this.november;
    }

    public Double getDecember() {
        return this.december;
    }

    public Double getYear() {
        return this.year;
    }
}