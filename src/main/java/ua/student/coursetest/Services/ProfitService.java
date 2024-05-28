package ua.student.coursetest.Services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.coursetest.Entity.ProfitEntity;
import ua.student.coursetest.Entity.ProfitTotalEntity;
import ua.student.coursetest.Entity.SpendingTotalEntity;
import ua.student.coursetest.Entity.UserEntity;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.ProfitTotalModel;
import ua.student.coursetest.Repository.ProfitRepository;
import ua.student.coursetest.Repository.ProfitTotalRepository;
import ua.student.coursetest.Repository.SpendingTotalRepository;
import ua.student.coursetest.Repository.UserRepository;
import ua.student.coursetest.Services.Interface.ProfitServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitService implements ProfitServiceInterface {

    private final ProfitRepository profitRepository;
    private final ProfitTotalRepository profitTotalRepository;
    private final UserRepository userRepository;
    private final HandlerUpdate update;
    private final SpendingTotalRepository spendingTotalRepository;

    public ProfitService(ProfitRepository profitRepository,
                         ProfitTotalRepository profitTotalRepository,
                         UserRepository userRepository, HandlerUpdate update,
                         SpendingTotalRepository spendingTotalRepository) {
        this.profitRepository = profitRepository;
        this.profitTotalRepository = profitTotalRepository;
        this.userRepository = userRepository;
        this.update = update;
        this.spendingTotalRepository = spendingTotalRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfitModel> findAllProfit(String email) throws DBIsEmptyException {
        List<ProfitModel> modelList = new ArrayList<>();
        List<ProfitEntity> list = profitRepository.findByUserProfitEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Profit is empty!");
        list.removeIf(a -> a.getArticle().equals("Balance at the beginning"));
        list.removeIf(a -> a.getArticle().equals("Opening balance"));
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    @Override
    public List<ProfitTotalModel> getAllTotalProfit(String email) throws DBIsEmptyException {
        List<ProfitTotalModel> modelList = new ArrayList<>();
        List<ProfitTotalEntity> list = profitTotalRepository.findByUserProfitTotalEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base TotalProfit is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    @Override
    public ProfitModel findProfit(String email, String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByUserProfitEmailAndArticle(email, article);
        if (profit == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }

        return profit.toModel();
    }

    //    public void startTable(ProfitModel model) {
//        if (profitRepository.findByArticle(model.getArticle()) == null) {
//            profitRepository.save(ProfitEntity.fromModel(update.addMonthToTable(model)));
//        }
//    }
    @Override
    public void saveProfit(String email, ProfitModel model) throws AlreadyExistException {
        UserEntity user = userRepository.findByEmail(email);
//        model.setUserProfit(user);
        if (profitRepository.existsProfitEntityByUserProfitEmailAndArticle(email, model.getArticle()))
            return;
//        ProfitModel.toModel(profitRepository.save(model));
//        if (profitRepository.existsByArticle(model.getArticle()))
//            return;
        ProfitModel profitModel = new ProfitModel(model.getArticle(), 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        if (model.getJanuary() != null)//todo
            profitModel.setJanuary(model.getJanuary());
        if (model.getFebruary() != null)
            profitModel.setFebruary(model.getFebruary());
        if (model.getMarch() != null)
            profitModel.setMarch(model.getMarch());
        if (model.getApril() != null)
            profitModel.setApril(model.getApril());
        if (model.getMay() != null)
            profitModel.setMay(model.getMay());
        if (model.getJune() != null)
            profitModel.setJune(model.getJune());
        if (model.getJuly() != null)
            profitModel.setJuly(model.getJuly());
        if (model.getAugust() != null)
            profitModel.setAugust(model.getAugust());
        if (model.getSeptember() != null)
            profitModel.setSeptember(model.getSeptember());
        if (model.getOctober() != null)
            profitModel.setOctober(model.getOctober());
        if (model.getNovember() != null)
            profitModel.setNovember(model.getNovember());
        if (model.getDecember() != null)
            profitModel.setDecember(model.getDecember());
        if (model.getYear() != null)
            profitModel.setYear(model.getYear());
        ProfitEntity profitEntity = ProfitEntity.fromModel(profitModel);
        profitEntity.setUserProfit(user);
//        user.addProfit(ProfitEntity.fromModel(profitModel));
//        userRepository.save(user);
        profitRepository.save(profitEntity);
        counterProfit(email);
    }


    //    @Transactional
//    public boolean addProfitTotal(ProfitTotalModel profitTotalModel) {
//        if (profitTotalRepository.existsByArticle(profitTotalModel.getArticle()))
//            return false;
//        ProfitTotalEntity profitTotalEntity = ProfitTotalEntity.fromModel(profitTotalModel);
//        profitTotalRepository.save(profitTotalEntity);
//        return true;
//    }
    @Override
    @Transactional
    public void updateProfit(String email, ProfitModel model) throws NotFoundException {
        UserEntity user = userRepository.findByEmail(email);
//        if (profitRepository.existsByUserProfitEmailAndArticle(email,model.getArticle()))
//            return;
        ProfitModel profitModel = profitRepository.getProfitEntityByUserProfitEmailAndArticle(email, model.getArticle()).toModel();
        if (profitModel == null) {
            return;
        }
        ProfitEntity profitEntity = ProfitEntity.fromModel(ModelUtils.addMonthToTable(model, profitModel));
        profitEntity.setUserProfit(user);
        profitRepository.save(profitEntity);
//        user.addProfit(ProfitEntity.fromModel(update.addMonthToTable(model,profitModel)));
//        userRepository.save(user);
//        profitRepository.save(ProfitEntity.fromModel(update.addMonthToTable(model, profitModel)));
        counterProfit(email);
    }

    @Override
    public ProfitModel findStartUpBalance(String email, String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByUserProfitEmailAndArticle(email, "Opening balance");
        if (profit != null) {
            return profit.toModel();
        }
        throw new NotFoundException("Could not find line with article " + article);
    }

    //    public void startUpBalance(ProfitModel model) throws NotFoundException{
//        ProfitModel profitModel = profitRepository.findByArticle(model.getArticle()).toModel();
//        if (!profitModel.getArticle().equals("Opening balance")) {
//            return;
//        }
//        profitRepository.save(ProfitEntity.fromModel(update.addMonthToTable(model, profitModel)));
//        counterProfit();
//    }
    @Override
    @Transactional
    public void countSumLine(String email) {
        profitRepository.sumProfitLine();
        countSum(email);
    }

    @Override
    @Transactional
    public void countSum(String email) { // Вертикальный расчет
        ProfitTotalEntity pte = profitTotalRepository.findByUserProfitTotalEmailAndArticle(email, "Total incomes");
        List<ProfitEntity> profitEntityList = profitRepository.findByUserProfitEmail(email);
        Double sumOfJanuary = profitEntityList.stream().map(ProfitEntity::getJanuary).toList().stream().mapToDouble(x -> x).sum();
        pte.setJanuary(sumOfJanuary);
        Double sumOfFebruary = profitEntityList.stream().map(ProfitEntity::getFebruary).toList().stream().mapToDouble(x -> x).sum();
        pte.setFebruary(sumOfFebruary);
        Double sumOfMarch = profitEntityList.stream().map(ProfitEntity::getMarch).toList().stream().mapToDouble(x -> x).sum();
        pte.setMarch(sumOfMarch);
        Double sumOfApril = profitEntityList.stream().map(ProfitEntity::getApril).toList().stream().mapToDouble(x -> x).sum();
        pte.setApril(sumOfApril);
        Double sumOfMay = profitEntityList.stream().map(ProfitEntity::getMay).toList().stream().mapToDouble(x -> x).sum();
        pte.setMay(sumOfMay);
        Double sumOfJune = profitEntityList.stream().map(ProfitEntity::getJune).toList().stream().mapToDouble(x -> x).sum();
        pte.setJune(sumOfJune);
        Double sumOfJuly = profitEntityList.stream().map(ProfitEntity::getJuly).toList().stream().mapToDouble(x -> x).sum();
        pte.setJuly(sumOfJuly);
        Double sumOfAugust = profitEntityList.stream().map(ProfitEntity::getAugust).toList().stream().mapToDouble(x -> x).sum();
        pte.setAugust(sumOfAugust);
        Double sumOfSeptember = profitEntityList.stream().map(ProfitEntity::getSeptember).toList().stream().mapToDouble(x -> x).sum();
        pte.setSeptember(sumOfSeptember);
        Double sumOfOctober = profitEntityList.stream().map(ProfitEntity::getOctober).toList().stream().mapToDouble(x -> x).sum();
        pte.setOctober(sumOfOctober);
        Double sumOfNovember = profitEntityList.stream().map(ProfitEntity::getNovember).toList().stream().mapToDouble(x -> x).sum();
        pte.setNovember(sumOfNovember);
        Double sumOfDecember = profitEntityList.stream().map(ProfitEntity::getDecember).toList().stream().mapToDouble(x -> x).sum();
        pte.setDecember(sumOfDecember);
        Double sumOfYear = profitEntityList.stream().map(ProfitEntity::getYear).toList().stream().mapToDouble(x -> x).sum();
        pte.setYear(sumOfYear);
        profitTotalRepository.save(pte);

//        Double jan = profitRepository.totalJan(email);
//        pte.setJanuary(jan);
//        Double feb = profitRepository.totalFeb();
//        pte.setFebruary(feb);
//        Double mar = profitRepository.totalMar();
//        pte.setMarch(mar);
//        Double apr = profitRepository.totalApr();
//        pte.setApril(apr);
//        Double ma = profitRepository.totalMay();
//        pte.setMay(ma);
//        Double jun = profitRepository.totalJun();
//        pte.setJune(jun);
//        Double jul = profitRepository.totalJul();
//        pte.setJuly(jul);
//        Double aug = profitRepository.totalAug();
//        pte.setAugust(aug);
//        Double sep = profitRepository.totalSep();
//        pte.setSeptember(sep);
//        Double oct = profitRepository.totalOct();
//        pte.setOctober(oct);
//        Double nov = profitRepository.totalNov();
//        pte.setNovember(nov);
//        Double dec = profitRepository.totalDec();
//        pte.setDecember(dec);
//        Double su = profitRepository.totalYear();
//        pte.setYear(su);
//        profitTotalRepository.save(pte);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void balance(String email) {

        ProfitEntity profitRest = profitRepository.getProfitEntityByUserProfitEmailAndArticle(email, "Balance at the beginning");
        ProfitTotalEntity pte = profitTotalRepository.getProfitTotalEntityByUserProfitTotalEmail(email);
        SpendingTotalEntity ste = spendingTotalRepository.getProfitTotalEntityByUserSpendingTotalEmail(email);
        double february = pte.getJanuary() - ste.getJanuary();
//        Double february = profitRepository.restForFebruary(email);
        if (profitRest.getJanuary() == 0 && february == 0 && profitRest.getFebruary() == 0
                || profitRest.getJanuary() == 0 && february == 0 && profitRest.getFebruary() != 0)
            profitRest.setFebruary(0.0);
        else
            profitRest.setFebruary(february);

        double march = pte.getFebruary() - ste.getFebruary();
        if (profitRest.getFebruary() == 0 && march == 0 && profitRest.getMarch() == 0
                || profitRest.getFebruary() == 0 && march == 0 && profitRest.getMarch() != 0)
            profitRest.setMarch(0.0);
        else
            profitRest.setMarch(march);

        double april = pte.getMarch() - ste.getMarch();
        if (profitRest.getMarch() == 0 && april == 0 && profitRest.getApril() == 0
                || profitRest.getMarch() == 0 && april == 0 && profitRest.getApril() != 0)
            profitRest.setApril(0.0);
        else
            profitRest.setApril(april);

        double may = pte.getApril() - ste.getApril();
        if (profitRest.getApril() == 0 && may == 0 && profitRest.getMay() == 0
                || profitRest.getApril() == 0 && may == 0 && profitRest.getMay() != 0)
            profitRest.setMay(0.0);
        else
            profitRest.setMay(may);

        double june = pte.getMay() - ste.getMay();
        if (profitRest.getMay() == 0 && june == 0 && profitRest.getJune() == 0
                || profitRest.getMay() == 0 && june == 0 && profitRest.getJune() != 0)
            profitRest.setJune(0.0);
        else
            profitRest.setJune(june);

        double july = pte.getJune() - ste.getJune();
        if (profitRest.getJune() == 0 && july == 0 && profitRest.getJuly() == 0
                || profitRest.getJune() == 0 && july == 0 && profitRest.getJuly() != 0)
            profitRest.setJuly(0.0);
        else
            profitRest.setJuly(july);

        double august = pte.getJuly() - ste.getJuly();
        if (profitRest.getJuly() == 0 && august == 0 && profitRest.getAugust() == 0
                || profitRest.getJuly() == 0 && august == 0 && profitRest.getAugust() != 0)
            profitRest.setAugust(0.0);
        else
            profitRest.setAugust(august);

        double september = pte.getAugust() - ste.getAugust();
        if (profitRest.getAugust() == 0 && september == 0 && profitRest.getSeptember() == 0
                || profitRest.getAugust() == 0 && september == 0 && profitRest.getSeptember() != 0)
            profitRest.setSeptember(0.0);
        else
            profitRest.setSeptember(september);

        double october = pte.getSeptember() - ste.getSeptember();
        if (profitRest.getSeptember() == 0 && october == 0 && profitRest.getOctober() == 0
                || profitRest.getSeptember() == 0 && october == 0 && profitRest.getOctober() != 0)
            profitRest.setOctober(0.0);
        else
            profitRest.setOctober(october);

        double november = pte.getOctober() - ste.getOctober();
        if (profitRest.getOctober() == 0 && november == 0 && profitRest.getNovember() == 0
                || profitRest.getOctober() == 0 && november == 0 && profitRest.getNovember() != 0)
            profitRest.setNovember(0.0);
        else
            profitRest.setNovember(november);

        double december = pte.getNovember() - ste.getNovember();
        if (profitRest.getNovember() == 0 && december == 0 && profitRest.getDecember() == 0
                || profitRest.getNovember() == 0 && december == 0 && profitRest.getDecember() != 0)
            profitRest.setDecember(0.0);
        else
            profitRest.setDecember(december);
        profitRepository.save(profitRest);
    }

    @Override
    @Transactional
    public void delete(String email, String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByUserProfitEmailAndArticle(email, article);
        if (profit == null) {
            throw new NotFoundException("Could not find article " + article + " and delete!");
        }
        if ("Balance at the beginning".equals(profit.getArticle()) && "Opening balance".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " can not be deleted");
        } else {
            profitRepository.deleteProfitEntityByArticle(article);
            counterProfit(email);
        }
    }

    @Override
    public void counterProfit(String email) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum(email);
            }
            balance(email);
        }
        countSumLine(email);
    }
}
