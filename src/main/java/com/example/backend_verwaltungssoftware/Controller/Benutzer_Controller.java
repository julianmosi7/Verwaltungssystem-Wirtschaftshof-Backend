package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.Benutzer;
import com.example.backend_verwaltungssoftware.Repositories.Benutzer_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/benutzer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Benutzer_Controller {

    @Autowired
    Benutzer_Repo benutzer_repo;

    @PostMapping(path = "/new_user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Benutzer create_benutzer(@RequestBody Benutzer benutzer){
        String encodePassword = Base64.getEncoder().encodeToString(benutzer.getPasswort().getBytes());
        benutzer.setPasswort(encodePassword);

        return benutzer_repo.save(benutzer);
    }

    @PostMapping(path = "/login_credentials", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Benutzer> getUser(@RequestBody Benutzer user){
        List<Benutzer> allusers = (List<Benutzer>) benutzer_repo.findAll();
        return allusers;

        /*for (int i = 0; i < allusers.size(); i++) {
            if(allusers.get(i).getEmail().equals(user.getEmail())){
                if(allusers.get(i).getPasswort().equals(user.getPasswort())){
                    return allusers.get(i);
                }
            }
        }
        return null;*/
    }
}
