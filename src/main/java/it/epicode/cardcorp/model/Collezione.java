package it.epicode.cardcorp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Collezione {

    @Id
    @GeneratedValue
    private  int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collezione", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartaCollezione> carte = new HashSet<>();
}
