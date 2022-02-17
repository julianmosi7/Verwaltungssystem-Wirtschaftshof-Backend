package com.example.backend_verwaltungssoftware.data.internalRepresentation;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Assignment {
    private int id;
    private Municipal municipal;
    Costcenter costCenter;
    String email;
    String assignmentLink;
    String assignmentDescription;
    List<User> personal;
    Date start;
    Date end;
    double progress;
    Status status;
    Boolean approved;
}
