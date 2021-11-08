package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
public class Rolle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rolle_id;
    String rolle;

    public Rolle() {
    }

    public Rolle(String rolle) {
        this.rolle = rolle;
    }

    public int getRolle_id() {
        return rolle_id;
    }

    public void setRolle_id(int rolle_id) {
        this.rolle_id = rolle_id;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
