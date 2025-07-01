package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.Carrello;
import it.epicode.cardcorp.model.CarrelloItem;
import it.epicode.cardcorp.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrelloItemRepository extends JpaRepository<CarrelloItem, Integer> {
    List<CarrelloItem> findByCarrello(Carrello carrello);
    void deleteByCarrelloAndCarta(Carrello carrello, Carta carta);
}

