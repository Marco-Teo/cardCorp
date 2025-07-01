package it.epicode.cardcorp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarelloDto {

    private int userId;
    private List<CarrelloItemDto> items;
}
