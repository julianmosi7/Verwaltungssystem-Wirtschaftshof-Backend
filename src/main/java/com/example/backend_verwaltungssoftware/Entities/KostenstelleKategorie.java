package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class KostenstelleKategorie {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int kostenKat_id;
    @OneToMany
    List<Kostenstelle> kostenstelleList;

    public KostenstelleKategorie() {
    }

    public KostenstelleKategorie(List<Kostenstelle> kostenstelleList) {
        this.kostenstelleList = kostenstelleList;
    }

    public int getKostenKat_id() {
        return kostenKat_id;
    }

    public void setKostenKat_id(int kostenKat_id) {
        this.kostenKat_id = kostenKat_id;
    }

    public List<Kostenstelle> getKostenstelleList() {
        return kostenstelleList;
    }

    public void setKostenstelleList(List<Kostenstelle> kostenstelleList) {
        this.kostenstelleList = kostenstelleList;
    }
}
