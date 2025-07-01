package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.Carrello;
import it.epicode.cardcorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {
    Optional<Carrello> findByUser(User user);
}
