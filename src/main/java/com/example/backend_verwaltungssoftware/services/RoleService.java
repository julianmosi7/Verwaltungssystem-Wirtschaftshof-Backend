package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.data.dtos.RoleDto;
import com.example.backend_verwaltungssoftware.data.entities.RoleEntity;
import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Role;
import com.example.backend_verwaltungssoftware.data.resources.RoleResource;
import com.example.backend_verwaltungssoftware.manager.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="roleService")
public class RoleService {

    @Autowired
    RoleManager roleManager;

    @Autowired
    private Role_Repo role_repo;

    public List<RoleResource> getAll(){
        List<RoleResource> result = new ArrayList<>();
        for (Role role :
                roleManager.getAll()) {
            result.add(convertRoletoToRoleResource(role));
        }
        return result;
    }

    public RoleEntity findByName(String name){
        RoleEntity roleEntity = role_repo.findByName(name);
        return roleEntity;
    }

    private RoleResource convertRoletoToRoleResource(Role role){
        RoleResource result = new RoleResource();
        result.setId(role.getId());
        result.setName(role.getName());
        return result;
    }

    private Role convertRoleDtoToRole(RoleDto roleDto){
        Role result = new Role();
        result.setId(-1);
        result.setName(roleDto.getName());
        return result;
    }
}
