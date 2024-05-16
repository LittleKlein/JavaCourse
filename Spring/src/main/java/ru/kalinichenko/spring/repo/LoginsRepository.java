package ru.kalinichenko.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kalinichenko.spring.entity.Logins;

public interface LoginsRepository extends JpaRepository<Logins, Long> {
    @Query("SELECT l.id FROM Logins l WHERE l.user_id = ?1")
    Long getLoginIdByUserId(Long userId);
}
