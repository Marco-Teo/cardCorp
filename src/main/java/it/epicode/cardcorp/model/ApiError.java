package it.epicode.cardcorp.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String message;
    private LocalDateTime dataErrore;
}