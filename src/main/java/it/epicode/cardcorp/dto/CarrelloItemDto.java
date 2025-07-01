package it.epicode.cardcorp.dto;

import lombok.Data;

@Data
public class CarrelloItemDto {

    private Long cartaId;
    private String nomeCarta;
    private int quantita;
    private double prezzo;
}