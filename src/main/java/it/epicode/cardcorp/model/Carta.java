package it.epicode.cardcorp.model;

import it.epicode.cardcorp.enumeration.Rarita;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Carta {
    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private String descrizione;
    private String urlImmagine;
    private double prezzo;
    private boolean preferita = false;
    private boolean inCollezione = false;

    @Enumerated(EnumType.STRING)
    private Rarita rarita;
}
