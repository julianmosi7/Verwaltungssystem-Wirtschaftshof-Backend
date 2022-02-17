package com.example.backend_verwaltungssoftware.data.entities;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@Data
@Entity
@Table(name = "holiday")
public class HolidayEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int holidayId;
    Date begin;
    Date end;
    String reason;
}
