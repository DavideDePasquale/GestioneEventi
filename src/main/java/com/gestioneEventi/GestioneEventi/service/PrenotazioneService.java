package com.gestioneEventi.GestioneEventi.service;

import com.gestioneEventi.GestioneEventi.model.Evento;
import com.gestioneEventi.GestioneEventi.model.Prenotazione;
import com.gestioneEventi.GestioneEventi.model.Utente;
import com.gestioneEventi.GestioneEventi.payload.PrenotazioneDTO;
import com.gestioneEventi.GestioneEventi.payload.mapper.PrenotazioneMapperDTO;
import com.gestioneEventi.GestioneEventi.repository.EventoRepository;
import com.gestioneEventi.GestioneEventi.repository.PrenotazioneRepository;
import com.gestioneEventi.GestioneEventi.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    PrenotazioneMapperDTO prenotazioneMapperDTO;
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    EventoRepository eventoRepository;



//    public String createPrenotazione(Long eventoId){
//
//
////        if (utenteOptional.isPresent() && )
////        return "Prenotazione per l'evento  " + prenotazione.getEvento() + " effettuata!☑️";
////    }
}
