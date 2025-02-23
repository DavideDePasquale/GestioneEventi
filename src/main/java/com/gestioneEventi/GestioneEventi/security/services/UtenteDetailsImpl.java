package com.gestioneEventi.GestioneEventi.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestioneEventi.GestioneEventi.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDetailsImpl implements UserDetails {

    // personalizziamo i dettagli che verranno inseriti nel token

    private Long idutente;
    private String username;
    private String email;
    @JsonIgnore // quando verranno inseriti i dettagli nel token, non verrà considerata la password perchè dato delicato
    private String password;
    private GrantedAuthority ruolo;

    public static UtenteDetailsImpl costruisciDettagli(Utente utente){
        //Conversione Set<Ruolo> in List <GrantedAuthority>
        // recupero Set e lo scansiono. Ogni ruolo che ci sarà nel Set, lo converto direttamente e dentro voglio avere il ruolo.
       GrantedAuthority ruoloUtente = new SimpleGrantedAuthority(utente.getRuolo().getTipo().name());
       return new UtenteDetailsImpl(
               utente.getIdutente(),
               utente.getUsername(),
               utente.getEmail(),
               utente.getPassword(),
               ruoloUtente
       );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(ruolo);
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
