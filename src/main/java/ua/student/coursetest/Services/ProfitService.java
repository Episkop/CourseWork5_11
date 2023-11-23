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
import ua.student.coursetest.Repository.ProfitEntityRepository;
import ua.student.coursetest.Repository.ProfitTotalEntityRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfitService {

    private final ProfitEntityRepository profitEntityRepository;
    private final ProfitTotalEntityRepository profitTotalEntityRepository;

    public ProfitService(ProfitEntityRepository profitEntityRepository,
                         ProfitTotalEntityRepository profitTotalEntityRepository) {
        this.profitEntityRepository = profitEntityRepository;
        this.profitTotalEntityRepository = profitTotalEntityRepository;
    }

    @Transactional(readOnly = true)
    public List<ProfitModel> findAllProfit() throws DBIsEmptyException {
        List<ProfitModel> modelList = new ArrayList<>();
        List<ProfitEntity> list = profitEntityRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Profit is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public List<ProfitTotalModel> getAllTotalProfit() throws DBIsEmptyException {
        List<ProfitTotalModel> modelList = new ArrayList<>();
        List<ProfitTotalEntity> list = profitTotalEntityRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base TotalProfit is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    public ProfitModel findProfit (String article) throws NotFoundException {
        ProfitEntity profit = profitEntityRepository.findByArticle(article);
        if(profit != null){
            return profit.toModel();
        }
        throw new  NotFoundException("Could not find line with article " + article );
    }

    public void saveProfit(ProfitModel model) throws AlreadyExistException {
        if(profitEntityRepository.findByArticle(model.getArticle()) == null) {
            profitEntityRepository.save(ProfitEntity.fromModel(model));
        }
        if(profitEntityRepository.findByArticle(model.getArticle()) != null){
            ProfitEntity profitModel = profitEntityRepository.findByArticle(model.getArticle());
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
            profitEntityRepository.save(profitModel);
        }
//        throw new AlreadyExistException("Could not save article "+ model.getArticle());
    }

//    @Transactional
//    public void updateProfit(ProfitModel model) throws NotFoundException{
//        ProfitModel profitModel = profitEntityRepository.findByArticle(model.getArticle()).toModel();
//        if(profitModel == null) {
//            return;
//        }
//        profitModel.setJanuary(model.getJanuary());
//        profitModel.setFebruary(model.getFebruary());
//        profitModel.setMarch(model.getMarch());
//        profitModel.setApril(model.getApril());
//        profitModel.setMay(model.getMay());
//        profitModel.setJune(model.getJune());
//        profitModel.setJuly(model.getJuly());
//        profitModel.setAugust(model.getAugust());
//        profitModel.setSeptember(model.getSeptember());
//        profitModel.setOctober(model.getOctober());
//        profitModel.setNovember(model.getNovember());
//        profitModel.setDecember(model.getDecember());
//        profitEntityRepository.save(ProfitEntity.fromModel(profitModel));
//    }


@Transactional
    public void delete(String article) throws NotFoundException {
       ProfitEntity profit = profitEntityRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Could not find article " + article + " and delete!");
        }
        profitEntityRepository.deleteProfitEntityByArticle(article);
    }
}
