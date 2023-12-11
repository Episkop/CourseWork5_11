package ua.student.coursetest.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.coursetest.Controllers.HandlerUpdate;
import ua.student.coursetest.Entity.SpendingEntity;
import ua.student.coursetest.Entity.SpendingTotalEntity;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.SpendingModel;
import ua.student.coursetest.Model.SpendingTotalModel;
import ua.student.coursetest.Repository.SpendingRepository;
import ua.student.coursetest.Repository.SpendingTotalRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final SpendingTotalRepository spendingTotalRepository;
    private final ProfitService profitService;
    private final HandlerUpdate update;

    public SpendingService(SpendingRepository spendingRepository, SpendingTotalRepository spendingTotalRepository, ProfitService profitService, HandlerUpdate update) {
        this.spendingRepository = spendingRepository;
        this.spendingTotalRepository = spendingTotalRepository;
        this.profitService = profitService;
        this.update = update;
    }


    @Transactional(readOnly = true)
    public List<SpendingModel> findAllSpending() throws DBIsEmptyException {
        List<SpendingModel> modelList = new ArrayList<>();
        List<SpendingEntity> list = spendingRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Spending is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public List<SpendingTotalModel> getAllTotalSpending() throws DBIsEmptyException {
        List<SpendingTotalModel> modelList = new ArrayList<>();
        List<SpendingTotalEntity> list = spendingTotalRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base TotalSpending is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public SpendingModel findSpending(String article) throws NotFoundException {
        SpendingEntity spending = spendingRepository.findByArticle(article);
        if (spending != null) {
            return spending.toModel();
        }
        throw new NotFoundException("Could not find line with article " + article);
    }

    public void startTable(SpendingModel model) {
        if (spendingRepository.findByArticle(model.getArticle()) == null) {
            spendingRepository.save(SpendingEntity.fromModel(update.addMonthToTable(model)));

        }
    }

    public void saveSpending(SpendingModel model) throws AlreadyExistException {
        if (spendingRepository.findByArticle(model.getArticle()) == null) {
            SpendingModel spendingModel = new SpendingModel(model.getArticle(), 0.0, 0.0, 0.0, 0.0,
                    0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            if (model.getJanuary() != null)
                spendingModel.setJanuary(model.getJanuary());
            if (model.getFebruary() != null)
                spendingModel.setFebruary(model.getFebruary());
            if (model.getMarch() != null)
                spendingModel.setMarch(model.getMarch());
            if (model.getApril() != null)
                spendingModel.setApril(model.getApril());
            if (model.getMay() != null)
                spendingModel.setMay(model.getMay());
            if (model.getJune() != null)
                spendingModel.setJune(model.getJune());
            if (model.getJuly() != null)
                spendingModel.setJuly(model.getJuly());
            if (model.getAugust() != null)
                spendingModel.setAugust(model.getAugust());
            if (model.getSeptember() != null)
                spendingModel.setSeptember(model.getSeptember());
            if (model.getOctober() != null)
                spendingModel.setOctober(model.getOctober());
            if (model.getNovember() != null)
                spendingModel.setNovember(model.getNovember());
            if (model.getDecember() != null)
                spendingModel.setDecember(model.getDecember());
            if (model.getYear() != null)
                spendingModel.setYear(model.getYear());
            spendingRepository.save(SpendingEntity.fromModel(spendingModel));
        }
        counterSpending();
    }


    @Transactional
    public boolean addSpendingTotal(SpendingTotalModel spendingTotalModelTotalModel) {
        if (spendingTotalRepository.existsByArticle(spendingTotalModelTotalModel.getArticle()))
            return false;
        SpendingTotalEntity spendingTotalEntity = SpendingTotalEntity.fromModel(spendingTotalModelTotalModel);
        spendingTotalRepository.save(spendingTotalEntity);
        return true;
    }

    @Transactional
    public void updateSpending(SpendingModel model) throws NotFoundException {
        SpendingModel spendingModel = spendingRepository.findByArticle(model.getArticle()).toModel();
        if (spendingModel == null) {
            return;
        }
        spendingRepository.save(SpendingEntity.fromModel(update.addMonthToTable(model, spendingModel)));
        counterSpending();
    }

    @Transactional
    public void countSumLineSpending() {
        spendingRepository.sumSpendingLine();
        countSumSE();
    }

    @Transactional
    public void countSumSE() {
        SpendingTotalEntity ste = spendingTotalRepository.findByArticle("Total expenses");
        Double jan = spendingRepository.totalJan();
        ste.setJanuary(jan);
        Double feb = spendingRepository.totalFeb();
        ste.setFebruary(feb);
        Double mar = spendingRepository.totalMar();
        ste.setMarch(mar);
        Double apr = spendingRepository.totalApr();
        ste.setApril(apr);
        Double ma = spendingRepository.totalMay();
        ste.setMay(ma);
        Double jun = spendingRepository.totalJun();
        ste.setJune(jun);
        Double jul = spendingRepository.totalJul();
        ste.setJuly(jul);
        Double aug = spendingRepository.totalAug();
        ste.setAugust(aug);
        Double sep = spendingRepository.totalSep();
        ste.setSeptember(sep);
        Double oct = spendingRepository.totalOct();
        ste.setOctober(oct);
        Double nov = spendingRepository.totalNov();
        ste.setNovember(nov);
        Double dec = spendingRepository.totalDec();
        ste.setDecember(dec);
        Double su = spendingRepository.totalYear();
        ste.setYear(su);
        spendingTotalRepository.save(ste);
    }


    @Transactional
    public void deleteSpending(String article) throws NotFoundException {
        spendingRepository.deleteSpendingEntityByArticle(article);
        counterSpending();

    }

    public void counterSpending() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSumSE();
                profitService.countSum();
            }
            countSumLineSpending();
            profitService.balance();
        }
        profitService.countSum();
        profitService.countSumLine();
    }
}




