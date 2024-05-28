package ua.student.coursetest.Services.Interface;

import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import ua.student.coursetest.Exception.AlreadyExistException;
import ua.student.coursetest.Exception.DBIsEmptyException;
import ua.student.coursetest.Exception.NotFoundException;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.ProfitTotalModel;

import java.util.List;

public interface ProfitServiceInterface {
    @Transactional(readOnly = true)
    List<ProfitModel> findAllProfit(String email) throws DBIsEmptyException;

    List<ProfitTotalModel> getAllTotalProfit(String email) throws DBIsEmptyException;

    ProfitModel findProfit(String email, String article) throws NotFoundException;

    void saveProfit(String email, ProfitModel model) throws AlreadyExistException;

    @Transactional
    void updateProfit(String email, ProfitModel model) throws NotFoundException;

    ProfitModel findStartUpBalance(String email, String article) throws NotFoundException;

    @Transactional
    void countSumLine(String email);

    @Transactional
    void countSum(String email);

    @SneakyThrows
    @Transactional
    void balance(String email);

    @Transactional
    void delete(String email, String article) throws NotFoundException;

    void counterProfit(String email);
}
