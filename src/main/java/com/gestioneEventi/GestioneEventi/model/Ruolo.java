package com.gestioneEventi.GestioneEventi.model;

import com.gestioneEventi.GestioneEventi.enumeration.ERuolo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ruoli")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idruolo;
    @Enumerated(EnumType.STRING)
    private ERuolo ruolo;

    public Ruolo(Long idruolo) {
        this.idruolo = idruolo;
    }
}
