package com.gestioneEventi.GestioneEventi.controller;

import com.gestioneEventi.GestioneEventi.payload.mapper.PrenotazioneMapperDTO;
import com.gestioneEventi.GestioneEventi.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;
    @Autowired
    PrenotazioneMapperDTO prenotazioneMapperDTO;
}
