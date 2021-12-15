package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.*;
import com.example.backend_verwaltungssoftware.Repositories.Benutzer_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Urlaub_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/urlaub")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Urlaub_Controller {

    @Autowired
    Urlaub_Repo urlaub_repo;

    @Autowired
    Benutzer_Repo benutzer_repo;

    @PostMapping(path = "/newHoliday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Urlaub create_auftrag(@RequestBody Urlaub urlaub){
        return urlaub_repo.save(urlaub);
    }

    @GetMapping(path = "/addHolidayToUser/{UserID}/{HolidayID}")
    public Benutzer addHolidayToUser(@PathVariable("UserID") int UserID, @PathVariable("HolidayID") int HolidayID){
        Optional<Benutzer> benutzer = benutzer_repo.findById(UserID);

        Optional<Urlaub> urlaub = urlaub_repo.findById(HolidayID);

        benutzer.get().getUrlaube().add(urlaub.get());

        return benutzer_repo.save(benutzer.get());
    }
}
