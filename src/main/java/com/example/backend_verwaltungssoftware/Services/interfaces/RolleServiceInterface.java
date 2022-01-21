package com.example.backend_verwaltungssoftware.Services.interfaces;

import com.example.backend_verwaltungssoftware.Entities.Rolle;

public interface RolleServiceInterface {
    Rolle findByName(String rolle);
}
