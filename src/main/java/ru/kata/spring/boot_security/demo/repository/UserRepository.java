package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User as u join fetch u.roles where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("from User as u join fetch u.roles")
    List<User> getAllUser();

    @Query("from User as u join fetch u.roles where u.id = :id")
    Optional<User> findById(@Param("id") Long id);
}
