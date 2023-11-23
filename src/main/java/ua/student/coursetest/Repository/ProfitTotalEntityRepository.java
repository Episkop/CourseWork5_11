package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.coursetest.Entity.ProfitTotalEntity;

public interface ProfitTotalEntityRepository extends JpaRepository<ProfitTotalEntity, Long> {

    boolean existsByArticle (String article);

    ProfitTotalEntity findByArticle (String article);


}