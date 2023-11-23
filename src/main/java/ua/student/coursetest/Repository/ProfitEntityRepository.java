package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.coursetest.Entity.ProfitEntity;

public interface ProfitEntityRepository extends JpaRepository<ProfitEntity, Long> {
    ProfitEntity findByArticle(String article);
    void deleteProfitEntityByArticle (String article);

    boolean existsByArticle (String article);

    ProfitEntity getProfitEntityByArticle (String article);
}