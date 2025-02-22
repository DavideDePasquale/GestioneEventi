package com.gestioneEventi.GestioneEventi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    //info che ritornerò all'interno di un oggetto JSON tramite ResponseEntity

    private String username;
    private Long idutente;
    private String email;
    private String ruolo;
    private String token;
    private String type = "Bearer ";
    //non ci deve essere la password perchè dato sensibile!

    public JwtResponse(String username,Long idutente,String email, String ruolo,String token){
        this.username = username;
        this.idutente = idutente;
        this.email = email;
        this.ruolo = ruolo;
        this.token = token;
    }
}
