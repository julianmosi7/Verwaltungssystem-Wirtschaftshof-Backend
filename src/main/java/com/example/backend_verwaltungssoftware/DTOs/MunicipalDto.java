package com.example.backend_verwaltungssoftware.DTOs;

import com.example.backend_verwaltungssoftware.Entities.Assignment;

import java.util.List;

public class MunicipalDto {
    private int id;
    private String name;
    private List<AssignmentDto> assignments;

    public MunicipalDto(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<AssignmentDto> getAssignments(){
        return assignments;
    }

    public void setAssignments(List<AssignmentDto> assignments){
        this.assignments = assignments;
    }
}
