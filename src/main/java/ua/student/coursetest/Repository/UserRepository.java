package ua.student.coursetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.coursetest.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);


}