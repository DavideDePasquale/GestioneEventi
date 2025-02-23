package com.gestioneEventi.GestioneEventi.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils utils;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // otteniamo jwt dai cookie http
        String jwt = analizzaJwt(request);
        // se la richiesta presenta un jwt, convalidiamo☑️
        if (jwt != null && utils.validazioneJwtToken(jwt)){
            //recupero username dal token jwt
            String username = utils.recuperoUsernameDaToken(jwt);
            //recuperiamo UserDetails da username e creiamo un ogg Authentication
            UserDetails dettagliUtente = userDetailsService.loadUserByUsername(username);
            // ora la creazione di oggetto di tipo UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken autenticazione =
                    new UsernamePasswordAuthenticationToken(dettagliUtente,null,dettagliUtente.getAuthorities());
            // set nei dettagli dell'ogg UsernamePas.....
            autenticazione.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // impostiamo lo UserDetails corrente nell'ambiente di Security
            SecurityContextHolder.getContext().setAuthentication(autenticazione);
        }


    }


    private String analizzaJwt(HttpServletRequest request){
        String headAutenticazione = request.getHeader("Authorization");
        //1. Controllo sulla presenza di un testo nel valore di Authorization
        //2. Controllo se il valore recuperato inizia per 'Bearer '
        // Bearer 34354gggkr3045
        if (StringUtils.hasText(headAutenticazione) && (headAutenticazione.startsWith("Bearer "))){
            //recupero la sottostringa escludendo la sequenza standard
            return headAutenticazione.substring(7);
        }
        return null;
    }
}
