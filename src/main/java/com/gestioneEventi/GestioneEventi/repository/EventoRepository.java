package com.gestioneEventi.GestioneEventi.repository;

import com.gestioneEventi.GestioneEventi.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
}
