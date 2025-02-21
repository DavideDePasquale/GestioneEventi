package com.gestioneEventi.GestioneEventi.repository;

import com.gestioneEventi.GestioneEventi.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {

    public Optional<Prenotazione> findById(Long id);

}
