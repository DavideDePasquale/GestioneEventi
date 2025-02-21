package com.gestioneEventi.GestioneEventi.controller;

import com.gestioneEventi.GestioneEventi.payload.mapper.EventoMapperDTO;
import com.gestioneEventi.GestioneEventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    EventoService eventoService;
    @Autowired
    EventoMapperDTO eventoMapperDTO;

}
