package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.coursetest.Entity.ProfitTotalEntity;
import ua.student.coursetest.Entity.SpendingTotalEntity;

import java.util.List;

public interface SpendingTotalRepository extends JpaRepository<SpendingTotalEntity, String> {
    SpendingTotalEntity findByUserSpendingTotalEmailAndArticle (String email, String article);
    List<SpendingTotalEntity> findByUserSpendingTotalEmail(String email);
    SpendingTotalEntity getProfitTotalEntityByUserSpendingTotalEmail (String email);


}