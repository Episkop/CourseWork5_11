package ua.student.coursetest.Model;

import ua.student.coursetest.Entity.ProfitEntity;

public class ProfitModel {
    private Long id;
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

    public ProfitModel(String article, Double january, Double february, Double march, Double april, Double may,
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
    public ProfitModel(Long id,String article, Double january, Double february, Double march, Double april, Double may,
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
    public ProfitModel() {
    }

    public static ProfitModel of(String article, Double january, Double february, Double march, Double april, Double may,
                                 Double june, Double july, Double august, Double september, Double october, Double november,
                                 Double december, Double year) {
        return new ProfitModel(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }
    public static ProfitModel of(Long id,String article, Double january, Double february, Double march, Double april, Double may,
                                 Double june, Double july, Double august, Double september, Double october, Double november,
                                 Double december, Double year) {
        return new ProfitModel(id,article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }
    public static ProfitModel toModel(ProfitEntity entity) {
        ProfitModel model = new ProfitModel(entity.getArticle(), 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        if (entity.getJanuary() != null)//todo
            model.setJanuary(entity.getJanuary());
        if (entity.getFebruary() != null)
           model.setFebruary(entity.getFebruary());
        if (entity.getMarch() != null)
            model.setMarch(entity.getMarch());
        if (entity.getApril() != null)
            model.setApril(entity.getApril());
        if (entity.getMay() != null)
            model.setMay(entity.getMay());
        if (entity.getJune() != null)
            model.setJune(entity.getJune());
        if (entity.getJuly() != null)
            model.setJuly(entity.getJuly());
        if (entity.getAugust() != null)
            model.setAugust(entity.getAugust());
        if (entity.getSeptember() != null)
            model.setSeptember(entity.getSeptember());
        if (entity.getOctober() != null)
            model.setOctober(entity.getOctober());
        if (entity.getNovember() != null)
            model.setNovember(entity.getNovember());
        if (entity.getDecember() != null)
            model.setDecember(entity.getDecember());
        if (entity.getYear() != null)
            model.setYear(entity.getYear());
//        model.setId(entity.getId());
//        model.setArticle(entity.getArticle());
//        model.setJanuary(entity.getJanuary());
//        model.setFebruary(entity.getFebruary());
//        model.setMarch(entity.getMarch());
//        model.setApril(entity.getApril());
//        model.setMay(entity.getMay());
//        model.setJune(entity.getJune());
//        model.setJuly(entity.getJuly());
//        model.setAugust(entity.getAugust());
//        model.setSeptember(entity.getSeptember());
//        model.setOctober(entity.getOctober());
//        model.setNovember(entity.getNovember());
//        model.setDecember(entity.getDecember());
//        model.setYear(entity.getYear());
        return model;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Double getJanuary() {
        return january;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public Double getFebruary() {
        return february;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public Double getMarch() {
        return march;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public Double getApril() {
        return april;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJune() {
        return june;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public Double getJuly() {
        return july;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public Double getAugust() {
        return august;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public Double getSeptember() {
        return september;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public Double getOctober() {
        return october;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public Double getNovember() {
        return november;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public Double getDecember() {
        return december;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }
}
