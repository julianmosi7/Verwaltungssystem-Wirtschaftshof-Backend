package com.example.backend_verwaltungssoftware.DTOs;

import java.sql.Date;
import java.util.List;

public class AssignmentDto {
    private int municipalId;
    private int costCenterId;
    private String email;
    private String path;
    private String link;
    private String assignmentDescription;
    private String task;
    private List<Integer> personal;
    private Date start;
    private Date end;
    private double progress;
    private int statusId;
    private Boolean approved;

    public int getMunicipalDto() {
        return municipalId;
    }

    public void setMunicipalDto(MunicipalDto municipalDto) {
        this.municipalId = municipalId;
    }

    public int getCostCenterDto() {
        return costCenterId;
    }

    public void setCostCenterDto(CostCenterDto costCenterDto) {
        this.costCenterId = costCenterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAssignmentDescription(){
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription){
        this.assignmentDescription = assignmentDescription;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<Integer> getPersonal() {
        return personal;
    }

    public void setPersonal(List<Integer> personal) {
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

    public int getStatusDto() {
        return statusId;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusId = statusId;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
