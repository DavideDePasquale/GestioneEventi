package com.gestioneEventi.GestioneEventi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "eventi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idevento;
    @Column(nullable = true)
    private String descrizione;
    @Column(nullable = false)
    private LocalDate dataevento;
    @Column(nullable = false)
    private String luogo;
    @Column(nullable = false)
    private String citta;
    @Column(nullable = false)
    private int capacita;

    private Utente organizzatore;

    public Evento(Long idevento) {
        this.idevento = idevento;
    }
}
