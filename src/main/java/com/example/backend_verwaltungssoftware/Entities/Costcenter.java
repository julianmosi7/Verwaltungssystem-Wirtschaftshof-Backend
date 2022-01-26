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
    String description;
    String category;

    public Costcenter() {
    }

    public Costcenter(int costcenter_id, String kost_id, String description, String category) {
        this.costcenter_id = costcenter_id;
        this.kost_id = kost_id;
        this.description = description;
        this.category = category;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
