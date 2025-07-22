package it.epicode.cardcorp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrdineDto {


    private LocalDate dataOrdine;

    private String indirizzo;

    private int userId;

    private List<OrdineCartaDto> carte;
}
