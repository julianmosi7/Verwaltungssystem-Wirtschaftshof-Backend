package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.data.entities.RoleEntity;
import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import com.example.backend_verwaltungssoftware.data.resources.RoleResource;
import com.example.backend_verwaltungssoftware.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Role_Controller {

    @Autowired
    RoleService roleService;

    @GetMapping(value = "/getAll")
    public HttpEntity<List<RoleResource>> getAll(){
        List<RoleResource> roleResources = roleService.getAll();
        return new HttpEntity<>(roleResources);
    }
}