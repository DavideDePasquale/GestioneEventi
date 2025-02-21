package com.gestioneEventi.GestioneEventi.payload.mapper;

import com.gestioneEventi.GestioneEventi.model.Utente;
import com.gestioneEventi.GestioneEventi.payload.UtenteDTO;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapperDTO {


    public UtenteDTO entityUtente_toDto(Utente entity){
        UtenteDTO dto = new UtenteDTO();
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
//        dto.setRuolo(entity.getRuolo());
        return dto;
    }


    public Utente utenteDto_toEntity(UtenteDTO dto){
        Utente entity = new Utente();
        entity.setNome(dto.getNome());
        entity.setCognome(dto.getCognome());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
//        entity.setRuolo(dto.getRuolo());
        return entity;
    }
}
