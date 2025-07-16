package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    boolean existsByEmail(String email);
    Optional<User> findOneByEmail(String email);
    Optional<User> findByUserNameAndEmail(String username, String email);
}
