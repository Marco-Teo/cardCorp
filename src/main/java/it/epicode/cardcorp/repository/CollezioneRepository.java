package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.Collezione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollezioneRepository extends JpaRepository<Collezione, Integer> {
    Optional<Collezione> findByUserId(int userId);
}
