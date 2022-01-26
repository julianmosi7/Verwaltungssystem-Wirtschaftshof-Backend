package com.example.backend_verwaltungssoftware.Services.interfaces;

import com.example.backend_verwaltungssoftware.DTOs.UserDto;
import com.example.backend_verwaltungssoftware.Entities.User;

import java.util.List;

public interface UserServiceInterface {
    User save(UserDto benutzer);
    List<User> findAll();
    User findOne(String username);
}
