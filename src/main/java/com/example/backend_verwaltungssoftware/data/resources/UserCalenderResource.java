package com.example.backend_verwaltungssoftware.data.resources;

import lombok.Data;

@Data
public class UserCalenderResource {
    private int id;
    private String name;

    public UserCalenderResource(int id, String name){
        this.id = id;
        this.name = name;
    }
}
