package com.example.backend_verwaltungssoftware.Controller;
import com.example.backend_verwaltungssoftware.Entities.*;
import com.example.backend_verwaltungssoftware.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/costcenter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Costcenter_Controller {
    @Autowired
    Costcenter_repo costcenter_repo;


    @PostMapping(value = "/newCostcenter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Costcenter create_costcenter(@RequestBody Costcenter costcenter) {
        return costcenter_repo.save(costcenter);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Costcenter> getAll_Costcenter(){
        List<Costcenter> costcenterList = (List<Costcenter>) costcenter_repo.findAll();
        return costcenterList;
    }
}
