package com.example.backend_verwaltungssoftware.data.entities;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@Table(name = "costcenter")
public class CostcenterEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int costCenterId;
    String cost_id;
    String description;
    String category;
}
