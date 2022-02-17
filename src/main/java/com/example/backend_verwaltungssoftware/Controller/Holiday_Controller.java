package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Holiday_Repo;
import com.example.backend_verwaltungssoftware.data.dtos.HolidayDto;
import com.example.backend_verwaltungssoftware.data.entities.HolidayEntity;
import com.example.backend_verwaltungssoftware.data.entities.UserEntity;
import com.example.backend_verwaltungssoftware.data.resources.HolidayResource;
import com.example.backend_verwaltungssoftware.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/holiday")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Holiday_Controller {

    @Autowired
    HolidayService holidayService;

    @Autowired
    Holiday_Repo holiday_repo;

    @Autowired
    User_Repo user_repo;

    @GetMapping(path = "/getAll")
    public HttpEntity<List<HolidayResource>> getAll(){
        List<HolidayResource> holidayResources = holidayService.getAll();
        return new HttpEntity<>(holidayResources);
    }

    @PostMapping(path = "/newHoliday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HolidayResource addHoliday(@RequestBody HolidayDto holidayDto){
        return holidayService.addHoliday(holidayDto);
    }
}
