package it.epicode.cardcorp.dto;

import it.epicode.cardcorp.enumeration.Rarita;
import lombok.Data;

@Data
public class FilterDto {
    private String nome;
    private Rarita rarita;
    private double min;
    private double max;
    private String order;
}
