package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.*;
import com.example.backend_verwaltungssoftware.Repositories.Auftrag_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Benutzer_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Gemeinde_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Status_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/auftrag")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Auftrag_Controller {

    @Autowired
    Auftrag_Repo auftrag_repo;
    @Autowired
    Benutzer_Repo benutzer_repo;
    @Autowired
    Gemeinde_Repo gemeinde_repo;
    @Autowired
    Status_Repo status_repo;

    @PostMapping(path = "/newAuftrag", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Auftrag create_auftrag(@RequestBody Auftrag auftrag){

        if(auftrag.getGemeinde() != null){
            Optional<Gemeinde> g = gemeinde_repo.findById(auftrag.getGemeinde().getGemeinde_id());
            auftrag.setGemeinde(g.get());
        }

        if(auftrag.getStatus() != null){
            Optional<Status> s = status_repo.findById(auftrag.getStatus().getStatus_id());
            auftrag.setStatus(s.get());
        }

        return auftrag_repo.save(auftrag);
    }

    @GetMapping(path = "/addPersonToAuftrag/{personID}/{auftragID}")
    public Auftrag addPersonToAuftrag(@PathVariable("personID") int pID,
                                      @PathVariable("auftragID") int aID){
        Optional<Benutzer> benutzer = benutzer_repo.findById(pID);
        Optional<Auftrag> auftrag = auftrag_repo.findById(aID);

        auftrag.get().getPersonal().add(benutzer.get());
        benutzer.get().getAuftraege().add(auftrag.get());

        auftrag_repo.deleteById(aID);
        return auftrag_repo.save(auftrag.get());
    }

    @GetMapping(path = "/deleteAuftrag{personID}")
    public void deleteAuftrag(@PathVariable("auftragID") int aID){
        Optional<Auftrag> auftrag = auftrag_repo.findById(aID);

        auftrag_repo.deleteById(aID);
    }

    @GetMapping(path = "/editEntry/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Auftrag editEntry(@PathVariable("id") int id, @RequestBody Auftrag newAuftrag){
        Optional<Auftrag> oldAuftrag = auftrag_repo.findById(id);

        oldAuftrag.get().setStatus(newAuftrag.getStatus());
        oldAuftrag.get().setGemeinde(newAuftrag.getGemeinde());
        oldAuftrag.get().setAufgabe(newAuftrag.getAufgabe());
        oldAuftrag.get().setDauer(newAuftrag.getDauer());
        oldAuftrag.get().setEmail(newAuftrag.getEmail());
        oldAuftrag.get().setEnd(newAuftrag.getEnd());
        oldAuftrag.get().setFortschritt(newAuftrag.getFortschritt());
        oldAuftrag.get().setKostenstelle(newAuftrag.getKostenstelle());
        oldAuftrag.get().setLink(newAuftrag.getLink());
        oldAuftrag.get().setPersonal(newAuftrag.getPersonal());
        oldAuftrag.get().setPfad(newAuftrag.getPfad());
        oldAuftrag.get().setStart(newAuftrag.getStart());

        return auftrag_repo.save(oldAuftrag.get());
    }
}
