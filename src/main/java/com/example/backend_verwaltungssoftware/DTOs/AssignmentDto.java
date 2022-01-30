package com.example.backend_verwaltungssoftware.DTOs;

import java.util.Date;
import java.util.List;

public class AssignmentDto {
    private MunicipalDto municipalDto;
    private CostCenterDto costCenterDto;
    private String email;
    private String path;
    private String link;
    private String task;
    private List<UserDto> personal;
    private Date start;
    private int duration;
    private Date end;
    private double progress;
    private StatusDto statusDto;
    private Boolean approved;

    public MunicipalDto getMunicipalDto() {
        return municipalDto;
    }

    public void setMunicipalDto(MunicipalDto municipalDto) {
        this.municipalDto = municipalDto;
    }

    public CostCenterDto getCostCenterDto() {
        return costCenterDto;
    }

    public void setCostCenterDto(CostCenterDto costCenterDto) {
        this.costCenterDto = costCenterDto;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<UserDto> getPersonal() {
        return personal;
    }

    public void setPersonal(List<UserDto> personal) {
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

    public StatusDto getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusDto = statusDto;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
