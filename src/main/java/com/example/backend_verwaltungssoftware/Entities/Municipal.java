package com.example.backend_verwaltungssoftware.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Municipal {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int municipalId;
    String name;
    @OneToMany(mappedBy = "municipal")
    List<Assignment> assignments;

    public Municipal() {
    }

    public Municipal(String name, List<Assignment> assignments) {
        this.name = name;
        this.assignments = assignments;
    }

    public int getMunicipalId() {
        return municipalId;
    }

    public void setMunicipalId(int municipalId) {
        this.municipalId = municipalId;
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
