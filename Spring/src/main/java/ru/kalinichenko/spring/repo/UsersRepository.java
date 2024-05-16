package ru.kalinichenko.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kalinichenko.spring.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsUsersByUsername(String username);

    @Query("SELECT u.id FROM Users u WHERE u.username = ?1")
    Long getIdByUsername(String username);
}
