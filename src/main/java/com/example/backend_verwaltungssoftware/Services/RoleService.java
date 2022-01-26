package com.example.backend_verwaltungssoftware.Services;

import com.example.backend_verwaltungssoftware.Entities.Role;
import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import com.example.backend_verwaltungssoftware.Services.interfaces.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="roleService")
public class RoleService implements RoleServiceInterface {

    @Autowired
    private Role_Repo role_repo;

    @Override
    public Role findByName(String name){
        Role role = role_repo.findByName(name);
        return role;
    }
}
