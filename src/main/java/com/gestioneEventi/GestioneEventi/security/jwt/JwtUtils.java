package com.gestioneEventi.GestioneEventi.security.jwt;

import com.gestioneEventi.GestioneEventi.security.services.UtenteDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

//funzionalità Utilities del token
@Component
public class JwtUtils {

    // prendiamo i valori delle costanti al jwt (quelle nell'application.properties)
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirations;



    // recupero keys
    public Key recuperoChiave(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //creazione jwt

    public String creaJwtToken(Authentication autenticazione){
        //recupero il dettaglio principale (username)
        UtenteDetailsImpl utentePrincipale = (UtenteDetailsImpl) autenticazione.getPrincipal(); // ci da indietro lo username

        // creazione jwt
        return Jwts.builder().setSubject(utentePrincipale.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date (new Date().getTime()+jwtExpirations))
                .signWith(recuperoChiave(), SignatureAlgorithm.HS256).compact();
    }


    // validazione token jwt
    public boolean validazioneJwtToken(String token){
        Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parse(token);
        return true;
    }

    // recupero username dal jwt

    public String recuperoUsernameDaToken(String token){
        String username = Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parseClaimsJwt(token).getBody().getSubject();
        return username;
    }



}
