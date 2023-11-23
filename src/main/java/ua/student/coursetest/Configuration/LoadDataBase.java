package ua.student.coursetest.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.student.coursetest.Model.ProfitModel;
//import ua.student.coursetest.Model.ProfitTotalModel;
//import ua.student.coursetest.Model.SpendingTotalModel;
//import ua.student.coursetest.Services.SpendingService;
import ua.student.coursetest.Services.ProfitService;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner commandLineRunner(final ProfitService profitService) { //, final SpendingService spendingService
        return args -> {
            profitService.saveProfit(new ProfitModel("Opening balance",null,null,null,null,null,null,null,null,null,null,null,null,null));
            profitService.saveProfit(new ProfitModel("Balance at the beginning",null,null,null,null,null,null,null,null,null,null,null,null,null));
            profitService.saveProfit(new ProfitModel("Salary",null,null,null,null,null,null,null,null,null,null,null,null,null));
            profitService.saveProfit(new ProfitModel("Credit",null,null,null,null,null,null,null,null,null,null,null,null,null));
            profitService.saveProfit(new ProfitModel("Deposit interest",null,null,null,null,null,null,null,null,null,null,null,null,null));
            profitService.saveProfit(new ProfitModel("Gifts",null,null,null,null,null,null,null,null,null,null,null,null,null));
            profitService.saveProfit(new ProfitModel("Sell",null,null,null,null,null,null,null,null,null,null,null,null,null));

//                spendingService.addSpending("Rental of property", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Communal expenses", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Products", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Alcohol", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Transport", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Car", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Gifts", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Sport", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Heals", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Other", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
//                spendingService.addSpending("Credit", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

//                profitService.addProfitTotal(new ProfitTotalModel("Total incomes",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
//
//                spendingService.addSpendingTotal(new SpendingTotalModel("Total expenses",0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

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

