package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.data.entities.StatusEntity;
import com.example.backend_verwaltungssoftware.Repositories.Status_Repo;
import com.example.backend_verwaltungssoftware.data.resources.StatusResource;
import com.example.backend_verwaltungssoftware.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/status")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Status_Controller {
    @Autowired
    StatusService statusService;

    @GetMapping(value = "/getAll")
    public HttpEntity<List<StatusResource>> getAll(){
        List<StatusResource> statusResources = statusService.getAll();
        return new HttpEntity<>(statusResources);
    }
}
