package it.epicode.cardcorp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ordine_carte")
public class OrdineCarta {

    @Id
    @GeneratedValue
    private int id;

    private int quantita;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "carta_id")
    private Carta carta;
}
