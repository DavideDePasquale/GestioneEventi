package com.gestioneEventi.GestioneEventi.controller;

import com.gestioneEventi.GestioneEventi.payload.UtenteDTO;
import com.gestioneEventi.GestioneEventi.payload.mapper.EventoMapperDTO;
import com.gestioneEventi.GestioneEventi.payload.request.LoginUtenteRequest;
import com.gestioneEventi.GestioneEventi.payload.response.JwtResponse;
import com.gestioneEventi.GestioneEventi.security.jwt.JwtUtils;
import com.gestioneEventi.GestioneEventi.security.services.UtenteDetailsImpl;
import com.gestioneEventi.GestioneEventi.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    AuthenticationManager managerAuth;
    @Autowired
    JwtUtils jwtUtils;


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



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginUtenteRequest loginDto, BindingResult validazione){

        //validazione
        if (validazione.hasErrors()){
            String errori = "Ci sono stati dei problemi nella validazione dei dati! \n";
            for (ObjectError msg : validazione.getAllErrors()){
                errori += msg.getDefaultMessage();
            }
            return new ResponseEntity<>(errori,HttpStatus.BAD_REQUEST);
        }
        // qui abbiamo passato la validazione, quindi possiamo generare il token
        UsernamePasswordAuthenticationToken tokenNoAuth = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());

        //invocare e recuperare l'authentication -> autenticazione ok
        //utilizziamo il gestiore delle autenticazioni che si basa su Username e password
        //Recupero autenticazione tramite metodo authenticate
        Authentication autenticazione = managerAuth.authenticate(tokenNoAuth);
        //Imposto l'autenticazione nel contesto di sicurezza di Spring
        SecurityContextHolder.getContext().setAuthentication(autenticazione);
        //genero il token finale sotto forma di stringa
        String token = jwtUtils.creaJwtToken(autenticazione);
        //recupero delle info che vogliamo inserire nella risp al client
        UtenteDetailsImpl dettagliUtente = (UtenteDetailsImpl) autenticazione.getPrincipal();
        String ruoloweb = dettagliUtente.getRuolo().getAuthority();
        //creo oggetto di tipo JwtResponse
        JwtResponse responseJwt = new JwtResponse(dettagliUtente.getUsername(),dettagliUtente.getIdutente(),dettagliUtente.getEmail(),ruoloweb,token);
        //gestione risp al client -> ResponseEntity
        return new ResponseEntity<>(responseJwt,HttpStatus.OK);
    }

}
