package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.data.entities.MunicipalEntity;
import com.example.backend_verwaltungssoftware.Repositories.Municipal_Repo;
import com.example.backend_verwaltungssoftware.data.resources.MunicipalResource;
import com.example.backend_verwaltungssoftware.services.MunicipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/municipal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Municipal_Controller {

    @Autowired
    MunicipalService municipalService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HttpEntity<List<MunicipalResource>> getAll(){
        List<MunicipalResource> municipalResources = municipalService.getAll();
        return new HttpEntity<>(municipalResources);
    }
}