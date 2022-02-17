package com.example.backend_verwaltungssoftware.data.resources;

import lombok.Data;

import java.sql.Date;

@Data
public class HolidayResource {
    private int id;
    Date begin;
    Date end;
    String reason;
}
