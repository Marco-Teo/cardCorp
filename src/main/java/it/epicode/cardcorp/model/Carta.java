package it.epicode.cardcorp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.cardcorp.enumeration.Rarita;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "carta")
    @JsonIgnore
    private Set<CartaCollezione> collezioni = new HashSet<>();
}
