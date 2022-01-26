package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Costcenter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int costcenter_id;
    String kost_id;
    String designation;
    String cathegory;

    public Costcenter() {
    }

    public Costcenter(String kost_id, String designation, String cathegory) {
        this.kost_id = kost_id;
        this.designation = designation;
        this.cathegory = cathegory;
    }

    public int getCostcenter_id() {
        return costcenter_id;
    }

    public void setCostcenter_id(int costcenter_id) {
        this.costcenter_id = costcenter_id;
    }

    public String getKost_id() {
        return kost_id;
    }

    public void setKost_id(String kost_id) {
        this.kost_id = kost_id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCathegory() {
        return cathegory;
    }

    public void setCathegory(String cathegory) {
        this.cathegory = cathegory;
    }
}
