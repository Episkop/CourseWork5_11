package ua.student.coursetest.Entity;


import lombok.Setter;
import ua.student.coursetest.Model.ProfitModel;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Table(name = "profit")
public class ProfitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserEntity userProfit;

    public UserEntity getUserProfit() {
        return userProfit;
    }

    public void setUserProfit(UserEntity userProfit) {
        this.userProfit = userProfit;
    }

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

    public ProfitEntity(Long id,String article, Double january, Double february, Double march, Double april, Double may,
                        Double june, Double july, Double august, Double september, Double october, Double november,
                        Double december, Double year) {
        this.id = id;
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

    public static ProfitEntity of(Long id,String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year){
        return new ProfitEntity(id,article, january, february, march, april, may, june, july, august,
                september, october, november, december,year);
    }
//    public static ProfitEntity fromModel(StartUpModel startUpModel) {
//        return ProfitEntity.of(startUpModel.getArticle(),startUpModel.getJanuary(),startUpModel.getFebruary(),startUpModel.getMarch(),startUpModel.getApril(),
//                startUpModel.getMay(), startUpModel.getJune(),startUpModel.getJuly(), startUpModel.getAugust(), startUpModel.getSeptember(), startUpModel.getOctober(),
//                startUpModel.getNovember(), startUpModel.getDecember(), startUpModel.getYear());
//    }

    public ProfitModel toModel() {
        return ProfitModel.of(id,article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }

    public static ProfitEntity fromModel (ProfitModel model){
        return ProfitEntity.of(model.getId(),model.getArticle(),model.getJanuary(),model.getFebruary(),model.getMarch(),model.getApril(),
                model.getMay(), model.getJune(),model.getJuly(), model.getAugust(), model.getSeptember(), model.getOctober(),
                model.getNovember(), model.getDecember(), model.getYear());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfitEntity profit)) return false;
        return article.equals(profit.article) && Objects.equals(january, profit.january)
                && Objects.equals(february, profit.february) && Objects.equals(march, profit.march)
                && Objects.equals(april, profit.april) && Objects.equals(may, profit.may)
                && Objects.equals(june, profit.june) && Objects.equals(july, profit.july)
                && Objects.equals(august, profit.august) && Objects.equals(september, profit.september)
                && Objects.equals(october, profit.october) && Objects.equals(november, profit.november)
                && Objects.equals(december, profit.december) && Objects.equals(year, profit.year)
                && userProfit.equals(profit.userProfit);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

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