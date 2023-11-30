package ua.student.coursetest.Services;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ua.student.coursetest.Entity.ProfitEntity;
import ua.student.coursetest.Entity.ProfitTotalEntity;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.ProfitTotalModel;
import ua.student.coursetest.Repository.ProfitRepository;
import ua.student.coursetest.Repository.ProfitTotalRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfitService {

    private final ProfitRepository profitRepository;
    private final ProfitTotalRepository profitTotalRepository;

    public ProfitService(ProfitRepository profitRepository,
                         ProfitTotalRepository profitTotalRepository) {
        this.profitRepository = profitRepository;
        this.profitTotalRepository = profitTotalRepository;
    }

    @Transactional(readOnly = true)
    public List<ProfitModel> findAllProfit() throws DBIsEmptyException {
        List<ProfitModel> modelList = new ArrayList<>();
        List<ProfitEntity> list = profitRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Profit is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public List<ProfitTotalModel> getAllTotalProfit() throws DBIsEmptyException {
        List<ProfitTotalModel> modelList = new ArrayList<>();
        List<ProfitTotalEntity> list = profitTotalRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base TotalProfit is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public ProfitModel findProfit(String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if (profit != null) {
            return profit.toModel();
        }
        throw new NotFoundException("Could not find line with article " + article);
    }

    public void saveProfit(ProfitModel model) throws AlreadyExistException {
        if (profitRepository.findByArticle(model.getArticle()) == null) {
            ProfitModel profitModel = new ProfitModel(model.getArticle(), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            if (model.getJanuary() != null)
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
            profitRepository.save(ProfitEntity.fromModel(profitModel));
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
    public boolean addProfitTotal(ProfitTotalModel profitTotalModel) {
        if (profitTotalRepository.existsByArticle(profitTotalModel.getArticle()))
            return false;
        ProfitTotalEntity profitTotalEntity = ProfitTotalEntity.fromModel(profitTotalModel);
        profitTotalRepository.save(profitTotalEntity);
        return true;
    }

    @Transactional
    public void updateProfit(ProfitModel model) throws NotFoundException {
        ProfitModel profitModel = profitRepository.findByArticle(model.getArticle()).toModel();
        if (profitModel == null) {
            return;
        }
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

//        for (int i = 0; i < 12; i++) {
//                for (int j = 0; j < 1; j++) {
//                    countSum();
//                }
//                balance();
//            }
//            countSumLine();
        profitRepository.save(ProfitEntity.fromModel(profitModel));
    }

    @Transactional
    public void countSumLine() {
        profitRepository.sumProfitLine();
        countSum();
    }

    @Transactional
    public void countSum() {
        ProfitTotalEntity pte = profitTotalRepository.findByArticle("Total incomes");
        Double jan = profitRepository.totalJan();
        pte.setJanuary(jan);
        Double feb = profitRepository.totalFeb();
        pte.setFebruary(feb);
        Double mar = profitRepository.totalMar();
        pte.setMarch(mar);
        Double apr = profitRepository.totalApr();
        pte.setApril(apr);
        Double ma = profitRepository.totalMay();
        pte.setMay(ma);
        Double jun = profitRepository.totalJun();
        pte.setJune(jun);
        Double jul = profitRepository.totalJul();
        pte.setJuly(jul);
        Double aug = profitRepository.totalAug();
        pte.setAugust(aug);
        Double sep = profitRepository.totalSep();
        pte.setSeptember(sep);
        Double oct = profitRepository.totalOct();
        pte.setOctober(oct);
        Double nov = profitRepository.totalNov();
        pte.setNovember(nov);
        Double dec = profitRepository.totalDec();
        pte.setDecember(dec);
        Double su = profitRepository.totalYear();
        pte.setYear(su);
        profitTotalRepository.save(pte);
    }

    @Transactional
    public void balance() {
        ProfitEntity profitRest = profitRepository.findByArticle("Balance at the beginning");
        Double february = profitRepository.restForFebruary();
        if (profitRest.getJanuary() == 0 && february == 0 && profitRest.getFebruary() == 0 || profitRest.getJanuary() == 0 && february == 0 && profitRest.getFebruary() != 0)
            ;
        else
            profitRest.setFebruary(february);
        Double march = profitRepository.restForMarch();
        if (profitRest.getFebruary() == 0 && march == 0 && profitRest.getMarch() == 0 || profitRest.getFebruary() == 0 && march == 0 && profitRest.getMarch() != 0)
            ;
        else
            profitRest.setMarch(march);
        Double april = profitRepository.restForApril();
        if (profitRest.getMarch() == 0 && april == 0 && profitRest.getApril() == 0 || profitRest.getMarch() == 0 && april == 0 && profitRest.getApril() != 0)
            ;
        else
            profitRest.setApril(april);
        Double may = profitRepository.restForMay();
        if (profitRest.getApril() == 0 && may == 0 && profitRest.getMay() == 0 || profitRest.getApril() == 0 && may == 0 && profitRest.getMay() != 0)
            ;
        else
            profitRest.setMay(may);
        Double june = profitRepository.restForJune();
        if (profitRest.getMay() == 0 && june == 0 && profitRest.getJune() == 0 || profitRest.getMay() == 0 && june == 0 && profitRest.getJune() != 0)
            ;
        else
            profitRest.setJune(june);
        Double july = profitRepository.restForJuly();
        if (profitRest.getJune() == 0 && july == 0 && profitRest.getJuly() == 0 || profitRest.getJune() == 0 && july == 0 && profitRest.getJuly() != 0)
            ;
        else
            profitRest.setJuly(july);
        Double august = profitRepository.restForAugust();
        if (profitRest.getJuly() == 0 && august == 0 && profitRest.getAugust() == 0 || profitRest.getJuly() == 0 && august == 0 && profitRest.getAugust() != 0)
            ;
        else
            profitRest.setAugust(august);
        Double september = profitRepository.restForSeptember();
        if (profitRest.getAugust() == 0 && september == 0 && profitRest.getSeptember() == 0 || profitRest.getAugust() == 0 && september == 0 && profitRest.getSeptember() != 0)
            ;
        else
            profitRest.setSeptember(september);
        Double october = profitRepository.restForOctober();
        if (profitRest.getSeptember() == 0 && october == 0 && profitRest.getOctober() == 0 || profitRest.getSeptember() == 0 && october == 0 && profitRest.getOctober() != 0)
            ;
        else
            profitRest.setOctober(october);
        Double november = profitRepository.restForNovember();
        if (profitRest.getOctober() == 0 && november == 0 && profitRest.getNovember() == 0 || profitRest.getOctober() == 0 && november == 0 && profitRest.getNovember() != 0)
            ;
        else
            profitRest.setNovember(november);
        Double december = profitRepository.restForDecember();
        if (profitRest.getNovember() == 0 && december == 0 && profitRest.getDecember() == 0 || profitRest.getNovember() == 0 && december == 0 && profitRest.getDecember() != 0)
            ;
        else
            profitRest.setDecember(december);
        profitRepository.save(profitRest);
    }

    @Transactional
    public void delete(String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Could not find article " + article + " and delete!");
        }
        if ("Balance at the beginning".equals(profit.getArticle()) && "Opening balance".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " can not be deleted");
        } else {
            profitRepository.deleteProfitEntityByArticle(article);
//            for (int i = 0; i < 12; i++) {
//                for (int j = 0; j < 1; j++) {
//                    countSum();
//                }
//                balance();
//            }
//            countSumLine();
        }
    }
}
