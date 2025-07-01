package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.Ordine;
import it.epicode.cardcorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByUser(User user);
}

