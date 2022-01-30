package com.example.backend_verwaltungssoftware.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Municipal {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int municipal_id;
    String name;
    @JsonIgnore
    @OneToMany(mappedBy = "Municipal")
    List<Assignment> assignments;

    public Municipal() {
    }

    public Municipal(String name, List<Assignment> assignments) {
        this.name = name;
        this.assignments = assignments;
    }

    public int getMunicipal_id() {
        return municipal_id;
    }

    public void setMunicipal_id(int municipal_id) {
        this.municipal_id = municipal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
