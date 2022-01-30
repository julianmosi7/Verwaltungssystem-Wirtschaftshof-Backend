package com.example.backend_verwaltungssoftware.DTOs;

import com.example.backend_verwaltungssoftware.Entities.Role;
import com.example.backend_verwaltungssoftware.Entities.User;

import java.sql.Date;

public class UserDto {
    private String username;
    private String passwort;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private RoleDto roleDto;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return passwort;
    }

    public void setPassword(String passwort){
        this.passwort = passwort;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }

    public RoleDto getRole(){
        return roleDto;
    }

    public void setRole(RoleDto roleDto){
        this.roleDto = roleDto;
    }

    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwort);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBirthdate(birthdate);

        return user;
    }
}
