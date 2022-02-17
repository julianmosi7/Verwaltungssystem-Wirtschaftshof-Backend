package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import com.example.backend_verwaltungssoftware.data.entities.RoleEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleManager {
    @Autowired
    private Role_Repo role_repo;

    public List<Role> getAll(){
        List<Role> roles = new ArrayList<>();

        for (RoleEntity roleEntity :
                role_repo.findAll()) {
            roles.add(convertRoleEntityToRole(roleEntity));
        }
        return roles;
    }

    public Role findById(int roleId){
        if(!role_repo.findById(roleId).isPresent()){
            //TODO: throw error
            return null;
        }else{
            return convertRoleEntityToRole(role_repo.findById(roleId).get());
        }
    }

    private Role convertRoleEntityToRole(RoleEntity roleEntity){
        Role result = new Role();
        result.setId(roleEntity.getRoleId());
        result.setName(roleEntity.getName());
        return result;
    }

    private RoleEntity convertRoleToRoleEntity(Role role){
        RoleEntity result = new RoleEntity();

        if(role.getId() != -1){
            result.setRoleId(role.getId());
        }
        result.setName(role.getName());
        return result;
    }
}
