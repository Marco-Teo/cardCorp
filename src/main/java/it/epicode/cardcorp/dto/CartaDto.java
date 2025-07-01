package it.epicode.cardcorp.dto;

import it.epicode.cardcorp.enumeration.Rarita;
import lombok.Data;

@Data
public class CartaDto {

    private String nome;
    private String descrizione;
    private String urlImmagine;
    private double prezzo;
    private boolean preferita;
    private boolean inCollezione;

    private Rarita rarita;
}
