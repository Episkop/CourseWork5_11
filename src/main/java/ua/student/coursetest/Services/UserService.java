package ua.student.coursetest.Services;

import org.springframework.stereotype.Service;
import ua.student.coursetest.Entity.*;
import ua.student.coursetest.Model.*;
import ua.student.coursetest.Repository.*;
import ua.student.coursetest.Services.Interface.UserServInterface;

import java.util.List;
@Service
public class UserService implements UserServInterface {
    private final UserRepository userRepository;
    private final ProfitRepository profitRepository;
    private final SpendingRepository spendingRepository;
    private final ProfitTotalRepository profitTotalRepository;
    private final SpendingTotalRepository spendingTotalRepository;

    public UserService(UserRepository userRepository, ProfitRepository profitRepository, SpendingRepository spendingRepository, ProfitTotalRepository profitTotalRepository, SpendingTotalRepository spendingTotalRepository) {
        this.userRepository = userRepository;
        this.profitRepository = profitRepository;
        this.spendingRepository = spendingRepository;
        this.profitTotalRepository = profitTotalRepository;
        this.spendingTotalRepository = spendingTotalRepository;
    }

    @Override
    public void addUser(UserModel userModel, List<ProfitModel> profitModels, List<SpendingModel> spendingModels,
                        ProfitTotalModel profitTotalModel, SpendingTotalModel spendingTotalModel) {
        if (userRepository.existsByEmail(userModel.getEmail()))
//        throw new AlreadyExistException("Such User " + userModel.getUsername() + " exist");
            return;

        UserEntity userEntity = UserEntity.fromModel(userModel);

        profitModels.forEach((x) -> {
            ProfitEntity profitEntity = ProfitEntity.fromModel(x);
            userEntity.addProfit(profitEntity);
        });
        spendingModels.forEach((x) -> {
            SpendingEntity spendingEntity = SpendingEntity.fromModel(x);
            userEntity.addSpending(spendingEntity);
        });

        ProfitTotalEntity profitTotalEntity = ProfitTotalEntity.fromModel(profitTotalModel);
        userEntity.addProfitTotal(profitTotalEntity);

        SpendingTotalEntity spendingTotalEntity = SpendingTotalEntity.fromModel(spendingTotalModel);
        userEntity.addSpendingTotal(spendingTotalEntity);

        userRepository.save(userEntity);
    }

    @Override
    public void delete(List<Long> idList) {
        idList.forEach(profitRepository::deleteById);
        idList.forEach(spendingRepository::deleteById);
    }
}
