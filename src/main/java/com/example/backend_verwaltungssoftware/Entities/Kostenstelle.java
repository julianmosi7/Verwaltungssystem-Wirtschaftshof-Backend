package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Kostenstelle{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int kostenstelle_id;
    String kost_id;
    String bezeichnung;

    public Kostenstelle() {
    }

    public Kostenstelle(String kost_id, String bezeichnung) {
        this.kost_id = kost_id;
        this.bezeichnung = bezeichnung;
    }

    public String getKost_id() {
        return kost_id;
    }

    public void setKost_id(String kost_id) {
        this.kost_id = kost_id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}