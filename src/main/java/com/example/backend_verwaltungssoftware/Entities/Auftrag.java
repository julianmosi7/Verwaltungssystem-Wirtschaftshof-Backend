package com.example.backend_verwaltungssoftware.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;

@Entity
@XmlRootElement
public class Auftrag {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Auftrag_id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gemeinde_id")
    Gemeinde gemeinde;
    @OneToOne
    Kostenstelle kostenstelle;
    String email;
    String pfad;
    String link;
    String aufgabe;
    @ManyToMany
    @JoinTable(name = "Auftrag_Personal",
            joinColumns = {@JoinColumn(name = "Auftrag_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Personal_ID")})
    List<Benutzer> personal;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date start;
    int dauer;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date end;
    double fortschritt;
    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;
    Boolean approved;

    public Auftrag() {
    }

    public Auftrag(Gemeinde gemeinde, Kostenstelle kostenstelle, String email, String pfad, String link, String aufgabe, List<Benutzer> personal, Date start, int dauer, Date end, double fortschritt, Status status) {
        this.gemeinde = gemeinde;
        this.kostenstelle = kostenstelle;
        this.email = email;
        this.pfad = pfad;
        this.link = link;
        this.aufgabe = aufgabe;
        this.personal = personal;
        this.start = start;
        this.dauer = dauer;
        this.end = end;
        this.fortschritt = fortschritt;
        this.status = status;
    }

    public int getAuftrag_id() {
        return Auftrag_id;
    }

    public void setAuftrag_id(int auftrag_id) {
        Auftrag_id = auftrag_id;
    }

    public Gemeinde getGemeinde() {
        return gemeinde;
    }

    public void setGemeinde(Gemeinde gemeinde) {
        this.gemeinde = gemeinde;
    }

    public Kostenstelle getKostenstelle() {
        return kostenstelle;
    }

    public void setKostenstelle(Kostenstelle kostenstelle) {
        this.kostenstelle = kostenstelle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPfad() {
        return pfad;
    }

    public void setPfad(String pfad) {
        this.pfad = pfad;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public List<Benutzer> getPersonal() {
        return personal;
    }

    public void setPersonal(List<Benutzer> personal) {
        this.personal = personal;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getFortschritt() {
        return fortschritt;
    }

    public void setFortschritt(double fortschritt) {
        this.fortschritt = fortschritt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
