package com.example.backend_verwaltungssoftware.data.resources;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AssignmentResource {
    private int id;
    MunicipalResource municipal;
    CostCenterResource costCenter;
    String email;
    String assignmentLink;
    String assignmentDescription;
    List<UserResource> personal;
    Date start;
    Date end;
    double progress;
    StatusResource status;
    boolean approved;
}
