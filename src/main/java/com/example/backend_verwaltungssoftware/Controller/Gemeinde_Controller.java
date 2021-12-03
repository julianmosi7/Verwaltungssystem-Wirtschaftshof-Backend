package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.Benutzer;
import com.example.backend_verwaltungssoftware.Entities.Führerschein;
import com.example.backend_verwaltungssoftware.Entities.Gemeinde;
import com.example.backend_verwaltungssoftware.Entities.Rolle;
import com.example.backend_verwaltungssoftware.Repositories.Gemeinde_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/gemeinde")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Gemeinde_Controller {

    @Autowired
    Gemeinde_Repo gemeinde_repo;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Gemeinde> getAll_Gemeinde(){
        List<Gemeinde> gemeindeList = (List<Gemeinde>) gemeinde_repo.findAll();
        return gemeindeList;
    }
}
