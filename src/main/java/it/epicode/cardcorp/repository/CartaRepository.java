package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.enumeration.Rarita;
import it.epicode.cardcorp.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaRepository extends JpaRepository<Carta, Integer> {
    List<Carta> findByNomeContainingIgnoreCase(String nome);
    List<Carta> findByPreferitaTrue();
    List<Carta> findByPrezzoBetween(int min, int max);
    List<Carta> findByRarita(Rarita rarita);
    List<Carta> findByInCollezioneTrue();
    List<Carta> findByInCollezioneFalse();
    List<Carta> findByInCollezione(boolean inCollezione);
    List<Carta> findByNomeContainingIgnoreCaseAndRaritaAndPrezzoBetween(
            String nome,
            Rarita rarita,
            double prezzoMin,
            double prezzoMax
    );
}
