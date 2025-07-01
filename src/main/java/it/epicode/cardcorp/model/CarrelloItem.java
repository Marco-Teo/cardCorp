package it.epicode.cardcorp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CarrelloItem {

    @Id
    @GeneratedValue
    private int id;
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "carrello_id")
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "carta_id")
    private Carta carta;
}
