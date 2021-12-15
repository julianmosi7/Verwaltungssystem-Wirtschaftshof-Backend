package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.Auftrag;
import com.example.backend_verwaltungssoftware.Entities.Benutzer;
import com.example.backend_verwaltungssoftware.Entities.Führerschein;
import com.example.backend_verwaltungssoftware.Entities.Rolle;
import com.example.backend_verwaltungssoftware.Repositories.Benutzer_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Führerschein_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Rolle_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/benutzer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Benutzer_Controller {

    @Autowired
    Benutzer_Repo Benutzer_Repo;
    @Autowired
    Rolle_Repo Rolle_repo;
    @Autowired
    Führerschein_Repo Führerschein_repo;

    @PostMapping(value = "/newUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Benutzer create_benutzer(@RequestBody Benutzer benutzer){
        String encodePassword = Base64.getEncoder().encodeToString(benutzer.getPasswort().getBytes());
        benutzer.setPasswort(encodePassword);

        if(benutzer.getRolle() != null){
            Optional<Rolle> r = Rolle_repo.findById(benutzer.getRolle().getRolle_id());
            benutzer.setRolle(r.get());
        }

        if(benutzer.getFührerscheine() != null){
            for (int i = 0; i < benutzer.getFührerscheine().size(); i++) {
                Optional<Führerschein> f = Führerschein_repo.findById(benutzer.getFührerscheine().get(i).getFührerschein_id());
                benutzer.getFührerscheine().add(f.get());
            }
        }

        return Benutzer_Repo.save(benutzer);
    }

    @GetMapping(value = "/loginCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public Benutzer getUser(@RequestBody Benutzer user){
        String encodePassword = Base64.getEncoder().encodeToString(user.getPasswort().getBytes());

        List<Benutzer> allusers = (List<Benutzer>) Benutzer_Repo.findAll();

        for (int i = 0; i < allusers.size(); i++) {
            if(allusers.get(i).getEmail().equals(user.getEmail())){
                if(allusers.get(i).getPasswort().equals(encodePassword)){
                    return allusers.get(i);
                }
            }
        }

        return null;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePerson(@PathVariable int id){
        try {
            Optional<Benutzer> p = Benutzer_Repo.findById(id);
            Benutzer_Repo.delete(p.get());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAll")
    public List<Benutzer> getAllUsers(){
        return (List<Benutzer>) Benutzer_Repo.findAll();
    }

    @GetMapping(path = "/editUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Benutzer editEntry(@PathVariable("id") int id, @RequestBody Benutzer newBenutzer){
        Optional<Benutzer> oldUser = Benutzer_Repo.findById(id);

        oldUser.get().setVorname(newBenutzer.getVorname());
        oldUser.get().setNachname(newBenutzer.getNachname());
        oldUser.get().setEmail(newBenutzer.getEmail());

        String encodePassword = Base64.getEncoder().encodeToString(newBenutzer.getPasswort().getBytes());
        oldUser.get().setPasswort(encodePassword);

        oldUser.get().setGeburtsdatum(newBenutzer.getGeburtsdatum());
        oldUser.get().setFührerscheine(newBenutzer.getFührerscheine());

        return Benutzer_Repo.save(oldUser.get());
    }
}
