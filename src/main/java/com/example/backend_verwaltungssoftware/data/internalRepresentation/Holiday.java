package com.example.backend_verwaltungssoftware.data.internalRepresentation;

import lombok.Data;

import java.sql.Date;

@Data
public class Holiday {
    private int id;
    Date begin;
    Date end;
    String reason;
}
