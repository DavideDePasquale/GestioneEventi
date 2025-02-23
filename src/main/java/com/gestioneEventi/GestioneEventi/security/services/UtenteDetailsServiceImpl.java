package com.gestioneEventi.GestioneEventi.security.services;

import com.gestioneEventi.GestioneEventi.model.Utente;
import com.gestioneEventi.GestioneEventi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UtenteDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> utente= utenteRepository.findByUsername(username);
        // mi recupero tutte le info dell'utente presente nel db
        Utente user = utente.orElseThrow();
        // Ritorna un ogg di tipo UtenteDetailsImpl
        // contiene info che dobbiamo inserire nel token!
        return UtenteDetailsImpl.costruisciDettagli(user);
    }
}
