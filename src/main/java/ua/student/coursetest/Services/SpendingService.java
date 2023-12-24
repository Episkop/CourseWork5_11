package ua.student.coursetest.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.coursetest.Entity.ProfitEntity;
import ua.student.coursetest.Entity.SpendingEntity;
import ua.student.coursetest.Entity.SpendingTotalEntity;
import ua.student.coursetest.Entity.UserEntity;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.SpendingModel;
import ua.student.coursetest.Model.SpendingTotalModel;
import ua.student.coursetest.Repository.SpendingRepository;
import ua.student.coursetest.Repository.SpendingTotalRepository;
import ua.student.coursetest.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final SpendingTotalRepository spendingTotalRepository;
    private final ProfitService profitService;
    private final UserRepository userRepository;
    private final HandlerUpdate update;

    public SpendingService(SpendingRepository spendingRepository, SpendingTotalRepository spendingTotalRepository,
                           ProfitService profitService, UserRepository userRepository, HandlerUpdate update) {
        this.spendingRepository = spendingRepository;
        this.spendingTotalRepository = spendingTotalRepository;
        this.profitService = profitService;
        this.userRepository = userRepository;
        this.update = update;
    }


    @Transactional(readOnly = true)
    public List<SpendingModel> findAllSpending(String email) throws DBIsEmptyException {
        List<SpendingModel> modelList = new ArrayList<>();
        List<SpendingEntity> list = spendingRepository.findByUserSpendingEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Spending is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public List<SpendingTotalModel> getAllTotalSpending(String email) throws DBIsEmptyException {
        List<SpendingTotalModel> modelList = new ArrayList<>();
        List<SpendingTotalEntity> list = spendingTotalRepository.findByUserSpendingTotalEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base TotalSpending is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public SpendingModel findSpending(String email, String article) throws NotFoundException {
        SpendingEntity spending = spendingRepository.findByUserSpendingEmailAndArticle(email, article);
        if (spending == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }

        return spending.toModel();
    }

//    public void startTable(SpendingModel model) {
//        if (spendingRepository.findByArticle(model.getArticle()) == null) {
//            spendingRepository.save(SpendingEntity.fromModel(update.addMonthToTable(model)));
//
//        }
//    }

    public void saveSpending(String email, SpendingModel model) throws AlreadyExistException {
        UserEntity user = userRepository.findByEmail(email);
        if(spendingRepository.existsProfitEntityByUserSpendingEmailAndArticle(email, model.getArticle()))
            return;
//        if (spendingRepository.existsByArticle(model.getArticle()))
//            return;
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
        SpendingEntity spendingEntity = SpendingEntity.fromModel(spendingModel);
        spendingEntity.setUserSpending(user);
        spendingRepository.save(spendingEntity);
//        user.addSpending(SpendingEntity.fromModel(spendingModel));
//            spendingRepository.save(SpendingEntity.fromModel(spendingModel));

        counterSpending(email);
    }


//    @Transactional
//    public boolean addSpendingTotal(SpendingTotalModel spendingTotalModelTotalModel) {
//        if (spendingTotalRepository.existsByArticle(spendingTotalModelTotalModel.getArticle()))
//            return false;
//        SpendingTotalEntity spendingTotalEntity = SpendingTotalEntity.fromModel(spendingTotalModelTotalModel);
//        spendingTotalRepository.save(spendingTotalEntity);
//        return true;
//    }

    @Transactional
    public void updateSpending(String email, SpendingModel model) throws NotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        SpendingModel spendingModel = spendingRepository.getSpendingEntityByUserSpendingEmailAndArticle(email, model.getArticle()).toModel();
        if (spendingModel == null) {
            return;
        }
        SpendingEntity spendingEntity = SpendingEntity.fromModel(update.addMonthToTable(model,spendingModel));
        spendingEntity.setUserSpending(user);
        spendingRepository.save(spendingEntity);
//        spendingRepository.save(SpendingEntity.fromModel(update.addMonthToTable(model, spendingModel)));
        counterSpending(email);
    }

    @Transactional
    public void countSumLineSpending(String email) {
        spendingRepository.sumSpendingLine();
        countSumSE(email);
    }

    @Transactional
    public void countSumSE(String email) {
        SpendingTotalEntity ste = spendingTotalRepository.findByUserSpendingTotalEmailAndArticle(email, "Total expenses");

        List<SpendingEntity> spendingEntityList = spendingRepository.findByUserSpendingEmail(email);
        Double sumOfJanuary = spendingEntityList.stream().map(SpendingEntity::getJanuary).toList().stream().mapToDouble(x->x).sum();
        ste.setJanuary(sumOfJanuary);

        Double sumOfFebruary = spendingEntityList.stream().map(SpendingEntity::getFebruary).toList().stream().mapToDouble(x->x).sum();
        ste.setFebruary(sumOfFebruary);
        Double sumOfMarch = spendingEntityList.stream().map(SpendingEntity::getMarch).toList().stream().mapToDouble(x->x).sum();
        ste.setMarch(sumOfMarch);
        Double sumOfApril = spendingEntityList.stream().map(SpendingEntity::getApril).toList().stream().mapToDouble(x->x).sum();
        ste.setApril(sumOfApril);
        Double sumOfMay = spendingEntityList.stream().map(SpendingEntity::getMay).toList().stream().mapToDouble(x->x).sum();
        ste.setMay(sumOfMay);
        Double sumOfJune = spendingEntityList.stream().map(SpendingEntity::getJune).toList().stream().mapToDouble(x->x).sum();
        ste.setJune(sumOfJune);
        Double sumOfJuly = spendingEntityList.stream().map(SpendingEntity::getJuly).toList().stream().mapToDouble(x->x).sum();
        ste.setJuly(sumOfJuly);
        Double sumOfAugust = spendingEntityList.stream().map(SpendingEntity::getAugust).toList().stream().mapToDouble(x->x).sum();
        ste.setAugust(sumOfAugust);
        Double sumOfSeptember = spendingEntityList.stream().map(SpendingEntity::getSeptember).toList().stream().mapToDouble(x->x).sum();
        ste.setSeptember(sumOfSeptember);
        Double sumOfOctober = spendingEntityList.stream().map(SpendingEntity::getOctober).toList().stream().mapToDouble(x->x).sum();
        ste.setOctober(sumOfOctober);
        Double sumOfNovember = spendingEntityList.stream().map(SpendingEntity::getNovember).toList().stream().mapToDouble(x->x).sum();
        ste.setNovember(sumOfNovember);
        Double sumOfDecember = spendingEntityList.stream().map(SpendingEntity::getDecember).toList().stream().mapToDouble(x->x).sum();
        ste.setDecember(sumOfDecember);
        Double sumOfYear = spendingEntityList.stream().map(SpendingEntity::getYear).toList().stream().mapToDouble(x->x).sum();
        ste.setYear(sumOfYear);
//        Double jan = spendingRepository.totalJan();
//        ste.setJanuary(jan);
//        Double feb = spendingRepository.totalFeb();
//        ste.setFebruary(feb);
//        Double mar = spendingRepository.totalMar();
//        ste.setMarch(mar);
//        Double apr = spendingRepository.totalApr();
//        ste.setApril(apr);
//        Double ma = spendingRepository.totalMay();
//        ste.setMay(ma);
//        Double jun = spendingRepository.totalJun();
//        ste.setJune(jun);
//        Double jul = spendingRepository.totalJul();
//        ste.setJuly(jul);
//        Double aug = spendingRepository.totalAug();
//        ste.setAugust(aug);
//        Double sep = spendingRepository.totalSep();
//        ste.setSeptember(sep);
//        Double oct = spendingRepository.totalOct();
//        ste.setOctober(oct);
//        Double nov = spendingRepository.totalNov();
//        ste.setNovember(nov);
//        Double dec = spendingRepository.totalDec();
//        ste.setDecember(dec);
//        Double su = spendingRepository.totalYear();
//        ste.setYear(su);
        spendingTotalRepository.save(ste);
    }


    @Transactional
    public void deleteSpending(String email, String article) throws NotFoundException {
        spendingRepository.deleteSpendingEntityByArticle(article);
        counterSpending(email);

    }

    public void counterSpending(String email) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSumSE(email);
                profitService.countSum(email);
            }
            countSumLineSpending(email);
            profitService.balance(email);
        }
        profitService.countSum(email);
        profitService.countSumLine(email);
    }
}




