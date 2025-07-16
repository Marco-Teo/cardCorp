package it.epicode.cardcorp.model;

import it.epicode.cardcorp.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;
    private String indirizzo;
    private String nome;
    private String cognome;

    @Enumerated(EnumType.STRING)
    private Role ruolo;



    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Carrello carrello;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ordine> ordini = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Collezione collezione;
}
