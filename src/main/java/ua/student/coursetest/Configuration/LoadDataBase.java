package ua.student.coursetest.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.student.coursetest.Model.ProfitModel;
import ua.student.coursetest.Model.ProfitTotalModel;
import ua.student.coursetest.Model.SpendingModel;
import ua.student.coursetest.Model.SpendingTotalModel;
import ua.student.coursetest.Services.ProfitService;
import ua.student.coursetest.Services.SpendingService;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner commandLineRunner(final ProfitService profitService, final SpendingService spendingService) {
        return args -> {
            profitService.saveProfit(new ProfitModel("Opening balance",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            profitService.saveProfit(new ProfitModel("Balance at the beginning",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            profitService.saveProfit(new ProfitModel("Salary",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            profitService.saveProfit(new ProfitModel("Credit",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            profitService.saveProfit(new ProfitModel("Deposit interest",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            profitService.saveProfit(new ProfitModel("Gifts",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            profitService.saveProfit(new ProfitModel("Sell",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

                spendingService.saveSpending(new SpendingModel("Rental of property", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Communal expenses", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Products", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Alcohol", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Transport", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Car", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Gifts", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Sport", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Heals", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Other", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                spendingService.saveSpending(new SpendingModel("Credit", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

                profitService.addProfitTotal(new ProfitTotalModel("Total incomes",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
//
                spendingService.addSpendingTotal(new SpendingTotalModel("Total expenses",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

        };
    }
}

//            private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//            @Bean
//            CommandLineRunner initDatabase(EmployeeRepository repository) {
//
//                return args -> {
//            log.info("Preloading " + repository.save(new ProfitEntity("Salary",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Credit",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Deposit",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Gifts",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Sell",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//       };

