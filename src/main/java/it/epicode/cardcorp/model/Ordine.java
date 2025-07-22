package it.epicode.cardcorp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ordine {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate dataOrdine;

    private String indirizzo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineCarta> carteOrdinate = new ArrayList<>();
}
