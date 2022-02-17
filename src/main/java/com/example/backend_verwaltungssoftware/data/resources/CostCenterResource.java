package com.example.backend_verwaltungssoftware.data.resources;

import lombok.Data;

@Data
public class CostCenterResource {
    private int id;
    String cost_id;
    String description;
    String category;
}
