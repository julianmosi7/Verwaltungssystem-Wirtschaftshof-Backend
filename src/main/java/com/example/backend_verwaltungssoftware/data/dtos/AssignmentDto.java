package com.example.backend_verwaltungssoftware.data.dtos;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
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
}
