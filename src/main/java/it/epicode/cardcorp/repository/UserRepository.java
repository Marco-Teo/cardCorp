package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    boolean existsByEmail(String email);
}
