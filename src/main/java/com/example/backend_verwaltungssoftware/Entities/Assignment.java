package com.example.backend_verwaltungssoftware.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int assignment_id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "municipal_id")
    Municipal municipal;
    @OneToOne
    Costcenter costcenter;
    String email;
    String pfad;
    String link;
    String task;
    @ManyToMany
    @JoinTable(name = "assignment_Personal",
            joinColumns = {@JoinColumn(name = "assignment_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<User> personal;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date start;
    int duration;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date end;
    double progress;
    @ManyToOne
    @JoinColumn(name = "Status_id")
    Status status;
    Boolean approved;

    public Assignment() {
    }

    public Assignment(com.example.backend_verwaltungssoftware.Entities.Municipal municipal, Costcenter costcenter, String email, String pfad, String link, String task, List<User> personal, Date start, int duration, Date end, double progress, Status status, Boolean approved) {
        this.municipal = municipal;
        this.costcenter = costcenter;
        this.email = email;
        this.pfad = pfad;
        this.link = link;
        this.task = task;
        this.personal = personal;
        this.start = start;
        this.duration = duration;
        this.end = end;
        this.progress = progress;
        this.status = status;
        this.approved = approved;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public com.example.backend_verwaltungssoftware.Entities.Municipal getMunicipal() {
        return municipal;
    }

    public void setMunicipal(com.example.backend_verwaltungssoftware.Entities.Municipal municipal) {
        this.municipal = municipal;
    }

    public Costcenter getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(Costcenter costcenter) {
        this.costcenter = costcenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPfad() {
        return pfad;
    }

    public void setPfad(String pfad) {
        this.pfad = pfad;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
