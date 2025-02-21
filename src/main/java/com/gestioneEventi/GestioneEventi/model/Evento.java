package com.gestioneEventi.GestioneEventi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eventi")
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

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;

    public Evento(Long idevento) {
        this.idevento = idevento;
    }
}
