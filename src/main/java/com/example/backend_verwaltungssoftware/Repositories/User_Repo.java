package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface User_Repo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
