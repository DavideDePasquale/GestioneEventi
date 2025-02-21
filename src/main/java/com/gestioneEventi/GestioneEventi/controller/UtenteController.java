package com.gestioneEventi.GestioneEventi.controller;

import com.gestioneEventi.GestioneEventi.payload.UtenteDTO;
import com.gestioneEventi.GestioneEventi.payload.mapper.EventoMapperDTO;
import com.gestioneEventi.GestioneEventi.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteService utenteService;
    @Autowired
    EventoMapperDTO eventoMapperDTO;


    @PostMapping("/new")
    public ResponseEntity<?> createUtente(@RequestBody @Validated UtenteDTO utenteDTO, BindingResult validazione){
        if(validazione.hasErrors()){
            String msg = "Utente non creato!❌" + "\n";
            for (ObjectError error : validazione.getAllErrors()){
                msg += error.getDefaultMessage();
            }
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
           String utente = utenteService.createUtente(utenteDTO);   // il ritorno del metodo nel service di "createUtente" è una stringa. Per questo ci metto un valore di ritorno di tipo Stringa.
        return new ResponseEntity<>(utente,HttpStatus.CREATED);
    }

}
