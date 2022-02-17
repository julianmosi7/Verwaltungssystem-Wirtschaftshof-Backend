package com.example.backend_verwaltungssoftware.data.resources;

import com.example.backend_verwaltungssoftware.data.internalRepresentation.Role;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class UserResource {
    private int id;
    private String username;
    private String password;
    String firstname;
    String lastname;
    String email;
    Date birthdate;
    RoleResource role;
    List<LicenceResource> licences;
    List<HolidayResource> holidays;
    List<AssignmentResource> assignments;
}
