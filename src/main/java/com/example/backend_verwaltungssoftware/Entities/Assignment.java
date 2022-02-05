package com.example.backend_verwaltungssoftware.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;

@Entity
@XmlRootElement
public class Assignment {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assignmentId;

    @ManyToOne
    @JoinColumn(name = "municipalId")
    private Municipal municipal;
    @OneToOne
    Costcenter costCenter;
    String email;
    String assignmentLink;
    String assignmentDescription;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "assignment_Personal",
            joinColumns = {@JoinColumn(name = "assignmentId")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<User> personal;
    Date start;
    Date end;
    double progress;
    @ManyToOne
    @JoinColumn(name = "statusId")
    Status status;
    Boolean approved;

    public Assignment() {
    }

    public Assignment(com.example.backend_verwaltungssoftware.Entities.Municipal municipal, Costcenter costCenter, String email, String pfad, String link, String task, List<User> personal, Date start, Date end, double progress, Status status, Boolean approved) {
        this.municipal = municipal;
        this.costCenter = costCenter;
        this.email = email;
        this.assignmentLink = assignmentLink;
        this.assignmentDescription = assignmentDescription;
        this.personal = personal;
        this.start = start;
        this.end = end;
        this.progress = progress;
        this.status = status;
        this.approved = approved;
    }

    public int getAssignment_id() {
        return assignmentId;
    }

    public void setAssignment_id(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Municipal getMunicipal() {
        return municipal;
    }

    public void setMunicipal(com.example.backend_verwaltungssoftware.Entities.Municipal municipal) {
        this.municipal = municipal;
    }



    public Costcenter getCostcenter() {
        return costCenter;
    }

    public void setCostcenter(Costcenter costcenter) {
        this.costCenter = costcenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return assignmentLink;
    }

    public void setLink(String assignmentLink) {
        this.assignmentLink = assignmentLink;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public List<User> getPersonal() {
        return personal;
    }

    public void setPersonal(List<User> personal) {
        this.personal = personal;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }



    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
