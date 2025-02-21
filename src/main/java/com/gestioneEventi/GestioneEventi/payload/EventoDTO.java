package com.gestioneEventi.GestioneEventi.payload;

import com.gestioneEventi.GestioneEventi.model.Utente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDTO {


    private String descrizione;
    @NotNull(message = "⚠️Il campo 'dataevento' è obbligatorio⚠️")
    private LocalDate dataevento;
    @NotBlank(message = "⚠️Il campo 'luogo' è obbligatorio⚠️")
    private String luogo;
    @NotBlank(message = "⚠️Il campo 'citta' è obbligatorio⚠️")
    private String citta;
    @NotNull(message = "⚠️Il campo 'capacita' è obbligatorio⚠️")
    private int capacita;
    private Utente organizzatore;
}
