package com.example.backend_verwaltungssoftware.Services;

import com.example.backend_verwaltungssoftware.Entities.Rolle;
import com.example.backend_verwaltungssoftware.Repositories.Rolle_Repo;
import com.example.backend_verwaltungssoftware.Services.interfaces.RolleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="roleService")
public class RolleService implements RolleServiceInterface {

    @Autowired
    private Rolle_Repo rolle_repo;

    @Override
    public Rolle findByName(String name){
        Rolle rolle = rolle_repo.findByName(name);
        return rolle;
    }
}
