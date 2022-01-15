package com.example.backend_verwaltungssoftware.DTOs;

import com.example.backend_verwaltungssoftware.Entities.Benutzer;

import java.sql.Date;

public class BenutzerDto {
    private String username;
    private String passwort;
    private String vorname;
    private String nachname;
    private String email;
    private Date geburtsdatum;

    public Benutzer getUserFromDto(){
        Benutzer benutzer = new Benutzer();
        benutzer.setUsername(username);
        benutzer.setPasswort(passwort);
        benutzer.setVorname(vorname);
        benutzer.setNachname(nachname);
        benutzer.setEmail(email);
        benutzer.setGeburtsdatum(geburtsdatum);

        return benutzer;
    }
}
