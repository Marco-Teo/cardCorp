package it.epicode.cardcorp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogInDto {
    @NotNull(message = "Password richiesta")
    String password;
    @Email(message = "Il formato dell'email non è corretto.")
    private String email;
}
