package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.*;
import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Holiday_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/holiday")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Holiday_Controller {

    @Autowired
    Holiday_Repo holiday_repo;

    @Autowired
    User_Repo user_repo;

    @PostMapping(path = "/newHoliday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Holiday create_auftrag(@RequestBody Holiday holiday){
        return holiday_repo.save(holiday);
    }

    @GetMapping(path = "/addHolidayToUser/{UserID}/{HolidayID}")
    public User addHolidayToUser(@PathVariable("UserID") int UserID, @PathVariable("HolidayID") int HolidayID){
        Optional<User> benutzer = user_repo.findById(UserID);

        Optional<Holiday> urlaub = holiday_repo.findById(HolidayID);

        benutzer.get().getHolidays().add(urlaub.get());

        return user_repo.save(benutzer.get());
    }
}
