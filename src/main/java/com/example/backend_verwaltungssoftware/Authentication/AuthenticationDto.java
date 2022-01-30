package com.example.backend_verwaltungssoftware.Authentication;

import com.example.backend_verwaltungssoftware.DTOs.RoleDto;

public class AuthenticationDto {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private RoleDto role;
    private JwtTokenResource jwtToken;

    public AuthenticationDto(String username, String firstname, String lastname,
                             RoleDto role, JwtTokenResource jwtToken){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.jwtToken = jwtToken;

    }
}
