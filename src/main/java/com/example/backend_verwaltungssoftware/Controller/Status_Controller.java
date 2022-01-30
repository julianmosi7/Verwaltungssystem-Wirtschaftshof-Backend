package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.Costcenter;
import com.example.backend_verwaltungssoftware.Entities.Status;
import com.example.backend_verwaltungssoftware.Repositories.Costcenter_repo;
import com.example.backend_verwaltungssoftware.Repositories.Status_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/status")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Status_Controller {
    @Autowired
    Status_Repo status_repo;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Status> getAll_Status(){
        List<Status> statusList = (List<Status>) status_repo.findAll();
        return statusList;
    }
}
