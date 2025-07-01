package it.epicode.cardcorp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrello {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL)
    private List<CarrelloItem> items = new ArrayList<>();
}
