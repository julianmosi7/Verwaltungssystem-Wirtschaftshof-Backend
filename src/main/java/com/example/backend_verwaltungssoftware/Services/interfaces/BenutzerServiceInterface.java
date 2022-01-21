package com.example.backend_verwaltungssoftware.Services.interfaces;

import com.example.backend_verwaltungssoftware.DTOs.BenutzerDto;
import com.example.backend_verwaltungssoftware.Entities.Benutzer;

import java.util.List;

public interface BenutzerServiceInterface {
    Benutzer save(BenutzerDto benutzer);
    List<Benutzer> findAll();
    Benutzer findOne(String username);
}
