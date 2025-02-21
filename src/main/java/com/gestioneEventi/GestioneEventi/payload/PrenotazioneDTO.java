package com.gestioneEventi.GestioneEventi.payload;

import com.gestioneEventi.GestioneEventi.model.Evento;
import com.gestioneEventi.GestioneEventi.model.Utente;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "⚠️Il campo 'Utente' non può essere vuoto⚠️")
    private Utente utente;
    @NotNull(message = "⚠️Il campo 'Evento' non può essere vuoto⚠️")
    private Evento evento;

}
