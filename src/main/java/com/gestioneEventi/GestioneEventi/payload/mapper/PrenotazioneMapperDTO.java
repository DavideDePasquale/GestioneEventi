package com.gestioneEventi.GestioneEventi.payload.mapper;

import com.gestioneEventi.GestioneEventi.model.Prenotazione;
import com.gestioneEventi.GestioneEventi.payload.PrenotazioneDTO;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapperDTO {


    public PrenotazioneDTO entityPrenotazione_toDto(Prenotazione entity){
        PrenotazioneDTO dto = new PrenotazioneDTO();
        dto.setEvento(entity.getEvento());
        dto.setUtente(entity.getUtente());
        return dto;
    }


    public Prenotazione prenotazioneDto_toEntity(PrenotazioneDTO dto){
        Prenotazione entity = new Prenotazione();
        entity.setEvento(dto.getEvento());
        entity.setUtente(dto.getUtente());
        return entity;
    }

}
