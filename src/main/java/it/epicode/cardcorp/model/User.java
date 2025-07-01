package it.epicode.cardcorp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String userName;
    private String email;
    private String password;
    private String indirizzo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Carrello carrello;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ordine> ordini = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Collezione collezione;
}
