package com.example.backend_verwaltungssoftware.DTOs;

public class UserCalenderDto {
    private int id;
    private String name;

    public UserCalenderDto() {

    }

    public UserCalenderDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
