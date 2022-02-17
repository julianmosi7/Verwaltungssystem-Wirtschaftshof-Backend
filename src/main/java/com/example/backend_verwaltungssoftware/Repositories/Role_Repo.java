package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.data.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role_Repo extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findByName(String role);
}
