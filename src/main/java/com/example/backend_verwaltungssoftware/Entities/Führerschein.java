package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
public class Führerschein implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int führerschein_id;
    String text;

    public Führerschein() {
    }

    public Führerschein(String text) {
        this.text = text;
    }

    public int getFührerschein_id() {
        return führerschein_id;
    }

    public void setFührerschein_id(int führerschein_id) {
        this.führerschein_id = führerschein_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
