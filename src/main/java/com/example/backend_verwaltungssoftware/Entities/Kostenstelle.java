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
    String kathegorie;

    public Kostenstelle() {
    }

    public Kostenstelle(String kost_id, String bezeichnung, String kathegorie) {
        this.kost_id = kost_id;
        this.bezeichnung = bezeichnung;
        this.kathegorie = kathegorie;
    }

    public int getKostenstelle_id() {
        return kostenstelle_id;
    }

    public void setKostenstelle_id(int kostenstelle_id) {
        this.kostenstelle_id = kostenstelle_id;
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

    public String getKathegorie() {
        return kathegorie;
    }

    public void setKathegorie(String kathegorie) {
        this.kathegorie = kathegorie;
    }
}
