package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.Entities.*;
import com.example.backend_verwaltungssoftware.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/auftrag")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Assignment_Controller {

    @Autowired
    Assignment_Repo assignment_repo;
    @Autowired
    User_Repo user_repo;
    @Autowired
    Municipal_Repo municipal_repo;
    @Autowired
    Status_Repo status_repo;
    @Autowired
    Costcenter_repo costcenter_repo;

    @PostMapping(path = "/newAuftrag")
    public Assignment create_auftrag(@RequestBody Assignment assignment){

        System.out.println(assignment.getCostcenter());
        System.out.println(assignment.toString());

        if(assignment.getMunicipal() != null){
            Optional<Municipal> g = municipal_repo.findById(assignment.getMunicipal().getMunicipalId());
            assignment.setMunicipal(g.get());
        }

        if(assignment.getStatus() != null){
            Optional<Status> s = status_repo.findById(assignment.getStatus().getStatusId());
            assignment.setStatus(s.get());
        }

        if(assignment.getCostcenter() != null){
            Optional<Costcenter> s = costcenter_repo.findById(assignment.getCostcenter().getCostCenterId());
            assignment.setCostcenter(s.get());
        }

        assignment.setApproved(false);

        return assignment_repo.save(assignment);
    }

    @GetMapping(path = "/addPersonToAuftrag/{personID}/{auftragID}")
    public boolean addPersonToAuftrag(@PathVariable("personID") int pID,
                                      @PathVariable("auftragID") int aID){
        Optional<User> benutzer = user_repo.findById(pID);
        Optional<Assignment> auftrag = assignment_repo.findById(aID);

        if(!auftrag.get().getApproved() || auftrag.get().getApproved() == null){
            return false;
        }

        auftrag.get().getPersonal().add(benutzer.get());
        benutzer.get().getAssignments().add(auftrag.get());

        assignment_repo.save(auftrag.get());
        return true;
    }

    @GetMapping(path = "/deleteAuftrag{personID}")
    public void deleteAuftrag(@PathVariable("auftragID") int aID){
        Optional<Assignment> auftrag = assignment_repo.findById(aID);

        assignment_repo.deleteById(aID);
    }

    @GetMapping(path = "/editEntry/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Assignment editEntry(@PathVariable("id") int id, @RequestBody Assignment newAssignment){
        Optional<Assignment> oldAuftrag = assignment_repo.findById(id);

        oldAuftrag.get().setStatus(newAssignment.getStatus());
        oldAuftrag.get().setMunicipal(newAssignment.getMunicipal());
        oldAuftrag.get().setAssignmentDescription(newAssignment.getAssignmentDescription());
        oldAuftrag.get().setDuration(newAssignment.getDuration());
        oldAuftrag.get().setEmail(newAssignment.getEmail());
        oldAuftrag.get().setEnd(newAssignment.getEnd());
        oldAuftrag.get().setProgress(newAssignment.getProgress());
        oldAuftrag.get().setCostcenter(newAssignment.getCostcenter());
        oldAuftrag.get().setLink(newAssignment.getLink());
        oldAuftrag.get().setPersonal(newAssignment.getPersonal());
        oldAuftrag.get().setStart(newAssignment.getStart());

        return assignment_repo.save(oldAuftrag.get());
    }

    @GetMapping(path = "/approveEntry/{id}")
    public Assignment approveEntry(@PathVariable("id") int id){
        Optional<Assignment> a = assignment_repo.findById(id);
        a.get().setApproved(true);
        return assignment_repo.save(a.get());
    }
}
