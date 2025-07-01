package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.Ordine;
import it.epicode.cardcorp.model.OrdineCarta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdineCartaRepository extends JpaRepository<OrdineCarta, Long> {
    List<OrdineCarta> findByOrdine(Ordine ordine);
}

