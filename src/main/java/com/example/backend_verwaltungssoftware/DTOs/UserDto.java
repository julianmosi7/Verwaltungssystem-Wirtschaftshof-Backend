package com.example.backend_verwaltungssoftware.DTOs;

import com.example.backend_verwaltungssoftware.Entities.User;

import java.sql.Date;

public class UserDto {
    private String username;
    private String passwort;
    private String vorname;
    private String nachname;
    private String email;
    private Date geburtsdatum;

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


    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwort);
        user.setFirstname(vorname);
        user.setLastname(nachname);
        user.setEmail(email);
        user.setBirthdate(geburtsdatum);

        return user;
    }
}
