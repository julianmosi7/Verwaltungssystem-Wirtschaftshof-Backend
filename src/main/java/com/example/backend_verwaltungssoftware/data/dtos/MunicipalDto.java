package com.example.backend_verwaltungssoftware.data.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MunicipalDto {
    private String name;
    private List<AssignmentDto> assignments;
}
