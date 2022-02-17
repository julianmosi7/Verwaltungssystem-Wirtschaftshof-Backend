package com.example.backend_verwaltungssoftware.data.internalRepresentation;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    String firstname;
    String lastname;
    String email;
    Date birthdate;
    Role role;
    List<Licence> licences;
    List<Holiday> holidays;
    List<Assignment> assignments;
}
