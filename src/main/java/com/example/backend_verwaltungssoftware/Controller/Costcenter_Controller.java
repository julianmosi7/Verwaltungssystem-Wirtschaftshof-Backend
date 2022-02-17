package com.example.backend_verwaltungssoftware.Controller;
;
import com.example.backend_verwaltungssoftware.Repositories.*;
import com.example.backend_verwaltungssoftware.data.dtos.CostCenterDto;
import com.example.backend_verwaltungssoftware.data.entities.CostcenterEntity;
import com.example.backend_verwaltungssoftware.data.resources.CostCenterResource;
import com.example.backend_verwaltungssoftware.manager.CostcenterManager;
import com.example.backend_verwaltungssoftware.services.CostcenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/costcenter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Costcenter_Controller {
    @Autowired
    CostcenterService costcenterService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HttpEntity<List<CostCenterResource>> getAll(){
        List<CostCenterResource> costCenterResources = costcenterService.getAll();
        return new HttpEntity<>(costCenterResources);
    }

    @PostMapping(value = "/newCostcenter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CostCenterResource addCostcenter(@RequestBody CostCenterDto costCenterDto) {
        return costcenterService.addCostcenter(costCenterDto);
    }
}
