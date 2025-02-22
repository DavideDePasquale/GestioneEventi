package com.gestioneEventi.GestioneEventi.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // 1. si setta il formato di ritorno verso il client
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 2. set status
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 3. set contenuto di ritorno verso il body del client
        final Map<String,Object> infoErrori = new HashMap<>();
        infoErrori.put("stato",HttpServletResponse.SC_UNAUTHORIZED);
        infoErrori.put("errore","Autorizzazione non valida!❌");
        infoErrori.put("message",authException.getMessage());
        infoErrori.put("path",request.getServletPath());
        /***   ESEMPIO DI RITORNO NEL BODY DEL CLIENT
         * "stato" : "SC_UNAUTHORIZED",
         * "errore" : Autorizzazione non valida,
         * "messaggio" : ..........
         * "path" : /api/auth/patchUtente
         */



        // 4. conversione da Map Java a Json
        final ObjectMapper mappaturaErrori = new ObjectMapper();
        mappaturaErrori.writeValue(response.getOutputStream(),infoErrori);

    }
}
