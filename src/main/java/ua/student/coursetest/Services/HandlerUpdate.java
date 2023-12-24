package ua.student.coursetest.Services;

import org.springframework.stereotype.Component;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.SpendingModel;

@Component
public class HandlerUpdate {

    public ProfitModel addMonthToTable (ProfitModel model){
        model.setJanuary(model.getJanuary());
        model.setFebruary(model.getFebruary());
        model.setMarch(model.getMarch());
        model.setApril(model.getApril());
        model.setMay(model.getMay());
        model.setJune(model.getJune());
        model.setJuly(model.getJuly());
        model.setAugust(model.getAugust());
        model.setSeptember(model.getSeptember());
        model.setOctober(model.getOctober());
        model.setNovember(model.getNovember());
        model.setDecember(model.getDecember());
        return model;
    }

    public SpendingModel addMonthToTable (SpendingModel model){
        model.setJanuary(model.getJanuary());
        model.setFebruary(model.getFebruary());
        model.setMarch(model.getMarch());
        model.setApril(model.getApril());
        model.setMay(model.getMay());
        model.setJune(model.getJune());
        model.setJuly(model.getJuly());
        model.setAugust(model.getAugust());
        model.setSeptember(model.getSeptember());
        model.setOctober(model.getOctober());
        model.setNovember(model.getNovember());
        model.setDecember(model.getDecember());
        return model;
    }

    public ProfitModel addMonthToTable (ProfitModel model,ProfitModel profitModel){
        profitModel.setId(model.getId());
        profitModel.setJanuary(model.getJanuary());
        profitModel.setFebruary(model.getFebruary());
        profitModel.setMarch(model.getMarch());
        profitModel.setApril(model.getApril());
        profitModel.setMay(model.getMay());
        profitModel.setJune(model.getJune());
        profitModel.setJuly(model.getJuly());
        profitModel.setAugust(model.getAugust());
        profitModel.setSeptember(model.getSeptember());
        profitModel.setOctober(model.getOctober());
        profitModel.setNovember(model.getNovember());
        profitModel.setDecember(model.getDecember());
        return profitModel;
    }

    public SpendingModel addMonthToTable (SpendingModel model,SpendingModel spendingModel){
        spendingModel.setJanuary(model.getJanuary());
        spendingModel.setFebruary(model.getFebruary());
        spendingModel.setMarch(model.getMarch());
        spendingModel.setApril(model.getApril());
        spendingModel.setMay(model.getMay());
        spendingModel.setJune(model.getJune());
        spendingModel.setJuly(model.getJuly());
        spendingModel.setAugust(model.getAugust());
        spendingModel.setSeptember(model.getSeptember());
        spendingModel.setOctober(model.getOctober());
        spendingModel.setNovember(model.getNovember());
        spendingModel.setDecember(model.getDecember());
        return spendingModel;
    }
}
