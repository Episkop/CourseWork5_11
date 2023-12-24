package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.coursetest.Entity.ProfitTotalEntity;

import java.util.List;

public interface ProfitTotalRepository extends JpaRepository<ProfitTotalEntity, Long> {

    ProfitTotalEntity findByUserProfitTotalEmailAndArticle (String email,String article);

    List<ProfitTotalEntity> findByUserProfitTotalEmail (String email);

    ProfitTotalEntity getProfitTotalEntityByUserProfitTotalEmail (String email);

}