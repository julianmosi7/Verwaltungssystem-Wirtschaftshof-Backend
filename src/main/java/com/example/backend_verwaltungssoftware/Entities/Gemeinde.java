package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Gemeinde {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gemeinde_id;
    String name;
    @OneToMany(mappedBy = "gemeinde")
    List<Auftrag> auftrage;

    public Gemeinde() {
    }

    public Gemeinde(String name, List<Auftrag> auftrage) {
        this.name = name;
        this.auftrage = auftrage;
    }

    public int getGemeinde_id() {
        return gemeinde_id;
    }

    public void setGemeinde_id(int gemeinde_id) {
        this.gemeinde_id = gemeinde_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Auftrag> getAuftrage() {
        return auftrage;
    }

    public void setAuftrage(List<Auftrag> auftrage) {
        this.auftrage = auftrage;
    }
}
