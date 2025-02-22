package com.gestioneEventi.GestioneEventi.security;

import com.gestioneEventi.GestioneEventi.security.jwt.AuthEntryPoint;
import com.gestioneEventi.GestioneEventi.security.services.UtenteDetailsImpl;
import com.gestioneEventi.GestioneEventi.security.services.UtenteDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // oltre ad essere un file di config, qui verà gestita la sicurezza globale dell'app. Spring trova la classe, la gestisce e ne configura l'ambiente di sicurezza.
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {

    @Autowired
    UtenteDetailsServiceImpl detailsImpl;

    @Autowired
    AuthEntryPoint gestioneNOAuthorization;

    //Spring crea in automatico un ogg Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //fornisce l'autenticazione attraverso i dettagli username e password
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        //gestione di come deve essere creato e inizializzato l'oggetto DaoAuth.....
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        // l'oggetto importa tutti i dettagli utente
        auth.setUserDetailsService(detailsImpl);
        //DaoAthent.... fornisce un metodo per accettare la password criptata
        auth.setPasswordEncoder(passwordEncoder());
        return auth;

    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authenticationProvider()).build();
    }



    // 1. Gestione csrf ( in questo caso lo disabilitiamo )
    // 2. Impostiamo il nostro gestore delle autorizz KO (se ci sono exception, qui impostiamo)
    // 3. Gestione sessione (sessionManagement) - proprietà della gestione token
    // 4. Gesione autorizz sulle richieste (a liv controller / a liv di singoli servizi controller)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(gestioneNOAuthorization))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/utente/**","/evento/getAll","/evento/getById").permitAll()
                                .requestMatchers("/evento/new","/evento/modify","/evento/delete").hasRole("ORGANIZZATORE")
                                .requestMatchers("/prenotazione/**").hasRole("NORMAL").anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider());
        return http.build(); // ci ritorna un SecurityFilterChain
    }
}
