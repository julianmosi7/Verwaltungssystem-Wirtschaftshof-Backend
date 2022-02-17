package com.example.backend_verwaltungssoftware.data.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class HolidayDto {
    Date begin;
    Date end;
    String reason;
}
