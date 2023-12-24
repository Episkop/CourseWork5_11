package ua.student.coursetest.Services.Interface;

import ua.student.coursetest.Model.*;

import java.util.List;

public interface UserServInterface {
    void addUser(UserModel userModel, List<ProfitModel> profitModels, List<SpendingModel> spendingModels,
                 ProfitTotalModel profitTotalModel, SpendingTotalModel spendingTotalModel);

    void delete(List<Long> idList);
}
