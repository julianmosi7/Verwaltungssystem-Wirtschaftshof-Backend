package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.Municipal;
import com.example.backend_verwaltungssoftware.Entities.Role;
import com.example.backend_verwaltungssoftware.Repositories.Municipal_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Role_Controller {

    @Autowired
    Role_Repo role_repo;

    //@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Role> getAll_Role(){
        return (List<Role>) role_repo.findAll();
    }
}