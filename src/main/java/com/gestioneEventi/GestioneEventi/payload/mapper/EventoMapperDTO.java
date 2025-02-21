package com.gestioneEventi.GestioneEventi.payload.mapper;

import com.gestioneEventi.GestioneEventi.model.Evento;
import com.gestioneEventi.GestioneEventi.payload.EventoDTO;
import org.springframework.stereotype.Component;

@Component
public class EventoMapperDTO {


    public EventoDTO entityEvento_to_Dto(Evento entity){
        EventoDTO dto = new EventoDTO();
        dto.setCapacita(entity.getCapacita());
        dto.setCitta(entity.getCitta());
        dto.setLuogo(entity.getLuogo());
        dto.setDescrizione(entity.getDescrizione());
        dto.setDataevento(entity.getDataevento());
        dto.setOrganizzatore(entity.getOrganizzatore());
        return dto;
    }

    public Evento eventoDto_toEntity(EventoDTO dto){
        Evento entity = new Evento();
        entity.setCapacita(dto.getCapacita());
        entity.setCitta(dto.getCitta());
        entity.setLuogo(dto.getLuogo());
        entity.setDescrizione(dto.getDescrizione());
        entity.setDataevento(dto.getDataevento());
        entity.setOrganizzatore(dto.getOrganizzatore());
        return entity;
    }
}
