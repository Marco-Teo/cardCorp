package it.epicode.cardcorp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.cardcorp.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "carta_id")
    )
    @JsonIgnore
    private Set<Carta> favoriteCards = new HashSet<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Carrello carrello;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Ordine> ordini = new ArrayList<>();


}
