package com.example.backend_verwaltungssoftware.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@XmlRootElement
public class Benutzer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int benutzer_id;
    private String username;
    @JsonIgnore
    private String passwort;
    String vorname;
    String nachname;
    String email;
    //TODO: loging with username or email??
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date geburtsdatum;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "Benutzer_Rolle",
            joinColumns = {@JoinColumn(name = "Benutzer_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Rolle_ID")})
    Rolle rolle;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Benutzer_Führerschein",
            joinColumns = {@JoinColumn(name = "Benutzer_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Führerschen_ID")})
    List<Führerschein> führerscheine;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Benutzer_Urlaub",
            joinColumns = {@JoinColumn(name = "Benutzer_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Urlaub_ID")})
    List<Urlaub> urlaube;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Benutzer_Auftrag",
            joinColumns = {@JoinColumn(name = "Benutzer_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Auftrag_ID")})
    List<Auftrag> auftraege;

    public Benutzer() {
    }

    public Benutzer(String username, String passwort, String vorname, String nachname, String email, Date geburtsdatum, Rolle rolle, List<Führerschein> führerscheine, List<Urlaub> urlaube, List<Auftrag> auftraege) {
        this.username = username;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.rolle = rolle;
        this.führerscheine = führerscheine;
        this.urlaube = urlaube;
        this.auftraege = auftraege;
    }

    public int getBenutzer_id() {
        return benutzer_id;
    }

    public void setBenutzer_id(int benutzer_id) {
        this.benutzer_id = benutzer_id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public List<Führerschein> getFührerscheine() {
        return führerscheine;
    }

    public void setFührerscheine(List<Führerschein> führerscheine) {
        this.führerscheine = führerscheine;
    }

    public List<Urlaub> getUrlaube() {
        return urlaube;
    }

    public void setUrlaube(List<Urlaub> urlaube) {
        this.urlaube = urlaube;
    }

    public List<Auftrag> getAuftraege() {
        return auftraege;
    }

    public void setAuftraege(List<Auftrag> auftraege) {
        this.auftraege = auftraege;
    }
}
