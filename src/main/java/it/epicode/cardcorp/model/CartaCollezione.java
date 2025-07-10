package it.epicode.cardcorp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartaCollezione {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "collezione_id")
    private Collezione collezione;

    @ManyToOne
    @JoinColumn(name = "carta_id")
    private Carta carta;
}
