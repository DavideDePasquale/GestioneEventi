package com.gestioneEventi.GestioneEventi.payload;

import com.gestioneEventi.GestioneEventi.model.Ruolo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UtenteDTO {


    @NotBlank(message = "⚠️Il campo 'nome' è obbligatorio⚠️")
    private String nome;
    @NotBlank(message = "⚠️Il campo 'cognome' è obbligatorio⚠️")
    private String cognome;
    @NotBlank(message = "⚠️Il campo 'email' è obbligatorio⚠️")
    @Email(message = "❌Indirizzo email non valido!❌")
    private String email;
    @NotBlank(message = "⚠️Il campo 'username' è obbligatorio⚠️")
    private String username;
    @NotBlank(message = "⚠️Il campo 'password' è obbligatorio⚠️")
    @Size(min = 6,max = 25)
    private String password;
    private Ruolo ruolo;
}
