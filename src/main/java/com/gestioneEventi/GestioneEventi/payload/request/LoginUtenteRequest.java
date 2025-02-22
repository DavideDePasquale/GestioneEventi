package com.gestioneEventi.GestioneEventi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUtenteRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6, max = 18)
    private String password;
}
