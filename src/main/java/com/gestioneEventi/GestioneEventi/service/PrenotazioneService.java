package com.gestioneEventi.GestioneEventi.service;

import com.gestioneEventi.GestioneEventi.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
}
