package ua.student.coursetest.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public SpendingService(SpendingRepository spendingRepository, SpendingTotalRepository spendingTotalRepository, ProfitService profitService) {
        this.spendingRepository = spendingRepository;
        this.spendingTotalRepository = spendingTotalRepository;
        this.profitService = profitService;
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

    public void saveSpending(SpendingModel model) throws AlreadyExistException {
        if (spendingRepository.findByArticle(model.getArticle()) == null) {
            spendingRepository.save(SpendingEntity.fromModel(model));
        }
//        if (profitEntityRepository.findByArticle(model.getArticle()) != null) {
//            ProfitEntity profitModel = profitEntityRepository.findByArticle(model.getArticle());
//            profitModel.setJanuary(model.getJanuary());
//            profitModel.setFebruary(model.getFebruary());
//            profitModel.setMarch(model.getMarch());
//            profitModel.setApril(model.getApril());
//            profitModel.setMay(model.getMay());
//            profitModel.setJune(model.getJune());
//            profitModel.setJuly(model.getJuly());
//            profitModel.setAugust(model.getAugust());
//            profitModel.setSeptember(model.getSeptember());
//            profitModel.setOctober(model.getOctober());
//            profitModel.setNovember(model.getNovember());
//            profitModel.setDecember(model.getDecember());
//            profitEntityRepository.save(profitModel);

//            for (int i = 0; i < 12; i++) {
//                for (int j = 0; j < 1; j++) {
//                    countSum();
//                }
//                balance();
//            }
//            countSumLine();
//            profitEntityRepository.save(profitModel);
//        }
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

//        for (int i = 0; i < 12; i++) {
//                for (int j = 0; j < 1; j++) {
//                    countSum();
//                }
//                balance();
//            }
//            countSumLine();
        spendingRepository.save(SpendingEntity.fromModel(spendingModel));
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
//        countSumSE();
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 1; j++) {
//                profitService.countSum();
//            }
//            profitService.balance();
//        }
    }
}


