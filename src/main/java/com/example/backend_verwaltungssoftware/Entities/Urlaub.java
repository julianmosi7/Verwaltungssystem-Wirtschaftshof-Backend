package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@Entity
@XmlRootElement
public class Urlaub {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Urlaub_id;
    Date begin;
    Date end;
    String grund;

    public Urlaub() {
    }

    public Urlaub(Date begin, Date end, String grund) {
        this.begin = begin;
        this.end = end;
        this.grund = grund;
    }

    public int getUrlaub_id() {
        return Urlaub_id;
    }

    public void setUrlaub_id(int urlaub_id) {
        Urlaub_id = urlaub_id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }
}
