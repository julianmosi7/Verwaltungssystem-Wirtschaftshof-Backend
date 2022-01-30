package com.example.backend_verwaltungssoftware.Services.interfaces;

import com.example.backend_verwaltungssoftware.Entities.Role;

public interface RoleServiceInterface {
    Role findByName(String rolle);
}
