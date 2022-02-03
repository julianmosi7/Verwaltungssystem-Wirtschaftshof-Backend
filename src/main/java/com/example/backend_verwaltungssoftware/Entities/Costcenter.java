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
    private int costCenterId;
    String cost_id;
    String description;
    String category;

    public Costcenter() {
    }

    public Costcenter(String cost_id, String description, String category) {
        this.cost_id = cost_id;
        this.description = description;
        this.category = category;
    }

    public int getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(int costcenterId) {
        this.costCenterId = costcenterId;
    }

    public String getCost_id() {
        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
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
