package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.student.coursetest.Entity.SpendingEntity;

import java.util.List;

public interface SpendingRepository extends JpaRepository<SpendingEntity, Long> {
    SpendingEntity findByUserSpendingEmailAndArticle (String email,String article);

    SpendingEntity getSpendingEntityByUserSpendingEmailAndArticle (String email,String article);
    boolean existsProfitEntityByUserSpendingEmailAndArticle (String email,String article);
    List<SpendingEntity> findByUserSpendingEmail (String email);
    boolean existsByArticle (String article);
    SpendingEntity findByArticle (String article);
    void deleteSpendingEntityByArticle (String article);
    @Transactional
    @Modifying
    @Query("UPDATE SpendingEntity e SET e.year = e.january + e.february + e.march + e.april + e.may + " +
            "e.june + e.july + e.august + e.september + e.october + e.november + e.december WHERE e.article = e.article")
    void sumSpendingLine();
    // Горизонтальный расчет

    @Query("SELECT sum (e.january) from SpendingEntity e ")
    Double totalJan();
    @Query("SELECT sum (e.february) from SpendingEntity e ")
    Double totalFeb();
    @Query("SELECT sum (e.march) from SpendingEntity e ")
    Double totalMar();
    @Query("SELECT sum (e.april) from SpendingEntity e ")
    Double totalApr();
    @Query("SELECT sum (e.may) from SpendingEntity e ")
    Double totalMay();
    @Query("SELECT sum (e.june) from SpendingEntity e ")
    Double totalJun();
    @Query("SELECT sum (e.july) from SpendingEntity e ")
    Double totalJul();
    @Query("SELECT sum (e.august) from SpendingEntity e ")
    Double totalAug();
    @Query("SELECT sum (e.september) from SpendingEntity e ")
    Double totalSep();
    @Query("SELECT sum (e.october) from SpendingEntity e ")
    Double totalOct();
    @Query("SELECT sum (e.november) from SpendingEntity e ")
    Double totalNov();
    @Query("SELECT sum (e.december) from SpendingEntity e ")
    Double totalDec();
    @Query("SELECT sum (e.year) from SpendingEntity e ")
    Double totalYear();

}