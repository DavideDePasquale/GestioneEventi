package com.gestioneEventi.GestioneEventi.service;

import com.gestioneEventi.GestioneEventi.model.Utente;
import com.gestioneEventi.GestioneEventi.payload.UtenteDTO;
import com.gestioneEventi.GestioneEventi.payload.mapper.UtenteMapperDTO;
import com.gestioneEventi.GestioneEventi.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    UtenteMapperDTO utenteMapperDTO;


    public String createUtente(UtenteDTO utenteDTO){
      Utente utente =  utenteMapperDTO.utenteDto_toEntity(utenteDTO);
      return "Utente " + utente.getUsername() + " è stato salvato nel DB con ID " + utente.getIdutente();
    }

    public String modifyUtente(UtenteDTO utenteDTO, Long id){
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente con ID "+ id + " non presente nel DB👎"));
        if (utenteDTO.getNome() != null && !utenteDTO.getNome().equals(utente.getNome())){
            utente.setNome(utenteDTO.getNome());
        }
        if (utenteDTO.getCognome() != null && !utenteDTO.getCognome().equals(utente.getCognome())){
            utente.setCognome(utenteDTO.getCognome());
        }
        if (utenteDTO.getUsername() != null && !utenteDTO.getUsername().equals(utente.getUsername()) && !utenteDTO.getUsername().equals(utenteRepository.existsByUsername(utente.getUsername()))){
            utente.setUsername(utenteDTO.getUsername());
        }
        if (utenteDTO.getEmail() != null && !utenteDTO.getEmail().equals(utente.getEmail()) && !utenteDTO.getEmail().equals(utenteRepository.existsByEmail(utente.getEmail()))){
            utente.setEmail(utenteDTO.getEmail());
        }
        if (utenteDTO.getPassword() != null && !utenteDTO.getPassword().equals(utente.getPassword())){
            utente.setPassword(utenteDTO.getPassword());
        }
        if (!utenteDTO.getRuolo().equals(utente.getRuolo())){
            utente.setRuolo(utenteDTO.getRuolo());
        }
        return "L'Utente " + utente.getUsername() + " è stato modificato correttamente! ☑️";
    }

    public String deleteUtente(Long id){
        Optional<Utente> utente = utenteRepository.findById(id);
        if(utente.isPresent()){
            utenteRepository.delete(utente.get());
            return "L'utente con ID " + id + "  è stato eliminato con successo!☑️";
        } else {
            throw new RuntimeException("Utente non trovato nel DB!👎");
        }
    }

    // mi recupero tutti gli utenti nel db
    public List<UtenteDTO> getAllUtenti(){
        List<Utente> listaUtenti = utenteRepository.findAll();
        List<UtenteDTO> listaUtentiDto = new ArrayList<>();
        for (Utente utente : listaUtenti){
            listaUtentiDto.add(utenteMapperDTO.entityUtente_toDto(utente));
        }
        return listaUtentiDto;
    }
    // mi recupero un singolo utente dal db
    public UtenteDTO getUtente(Long id){
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isPresent()){
            return utenteMapperDTO.entityUtente_toDto(utente.get());
        } else {
            throw new RuntimeException("Utente con ID " + id + " non è presente nel DB!❌");
        }
    }

}
