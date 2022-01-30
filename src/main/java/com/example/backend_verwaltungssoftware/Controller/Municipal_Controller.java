package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.DTOs.AssignmentDto;
import com.example.backend_verwaltungssoftware.DTOs.MunicipalDto;
import com.example.backend_verwaltungssoftware.Entities.Municipal;
import com.example.backend_verwaltungssoftware.Repositories.Municipal_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/municipal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Municipal_Controller {

    @Autowired
    Municipal_Repo municipal_repo;

    //@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Municipal> getAll_Gemeinde(){
        return (List<Municipal>) municipal_repo.findAll();
    }
}
