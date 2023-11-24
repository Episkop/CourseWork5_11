package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.coursetest.Entity.SpendingTotalEntity;

public interface SpendingTotalRepository extends JpaRepository<SpendingTotalEntity, String> {

    boolean existsByArticle (String article);

    SpendingTotalEntity findByArticle (String article);
}