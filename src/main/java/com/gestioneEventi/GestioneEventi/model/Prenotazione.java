package com.gestioneEventi.GestioneEventi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprenotazione;
    @ManyToOne
    @JoinColumn(name = "utenteid")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "eventoid")
    private Evento evento;

    public Prenotazione(Long idprenotazione) {
        this.idprenotazione = idprenotazione;
    }
}
