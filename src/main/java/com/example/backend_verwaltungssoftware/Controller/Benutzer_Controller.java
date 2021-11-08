package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.Benutzer;
import com.example.backend_verwaltungssoftware.Repositories.Benutzer_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/benutzer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Benutzer_Controller {

    @Autowired
    Benutzer_Repo benutzer_repo;

    @PostMapping(path = "/new_user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Benutzer create_benutzer(@RequestBody Benutzer benutzer){
        return benutzer_repo.save(benutzer);
    }

    @GetMapping(path = "/login_credentials/{email}/{password}")
    public Benutzer getUser(@PathVariable("email") String email,
                            @PathVariable("password") String password){

        List<Benutzer> allusers = (List<Benutzer>) benutzer_repo.findAll();

        for (int i = 0; i < allusers.size(); i++) {
            if(allusers.get(i).getEmail().equals(email)){
                if(allusers.get(i).getPasswort().equals(password)){
                    return allusers.get(i);
                }
            }
        }
        return null;
    }
}
