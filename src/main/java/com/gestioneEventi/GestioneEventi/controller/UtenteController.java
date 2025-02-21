package com.gestioneEventi.GestioneEventi.controller;

import com.gestioneEventi.GestioneEventi.payload.mapper.EventoMapperDTO;
import com.gestioneEventi.GestioneEventi.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteService utenteService;
    @Autowired
    EventoMapperDTO eventoMapperDTO;
}
