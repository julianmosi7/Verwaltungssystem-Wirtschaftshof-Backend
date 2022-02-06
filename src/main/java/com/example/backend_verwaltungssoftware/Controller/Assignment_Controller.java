package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.DTOs.AssignmentDto;
import com.example.backend_verwaltungssoftware.Entities.*;
import com.example.backend_verwaltungssoftware.Mail.EmailSenderService;
import com.example.backend_verwaltungssoftware.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/assignment")
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
    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping(path = "/newAssignment")
    public Assignment create_auftrag(@RequestBody AssignmentDto assignmentDto){

        Assignment assignment = new Assignment();

        assignment.setEmail(assignmentDto.getEmail());
        assignment.setLink(assignmentDto.getLink());
        assignment.setAssignmentDescription(assignmentDto.getAssignmentDescription());
        assignment.setStart(assignmentDto.getStart());
        assignment.setEnd(assignmentDto.getEnd());
        assignment.setProgress(assignmentDto.getProgress());
        Optional<Municipal> municipal = municipal_repo.findById(assignmentDto.getMunicipalDto());
        assignment.setMunicipal(municipal.get());
        Optional<Status> status = status_repo.findById(assignmentDto.getStatusDto());
        assignment.setStatus(status.get());
        Optional<Costcenter> costCenter = costcenter_repo.findById(assignmentDto.getCostCenterDto());
        assignment.setCostcenter(costCenter.get());
        List<User> personal = (List<User>)user_repo.findAllById(assignmentDto.getPersonal());
        assignment.setPersonal(personal);

        assignment.setApproved(false);

        return assignment_repo.save(assignment);
    }

    @GetMapping(path = "/addPersonToAssignment/{personID}/{auftragID}/{senderID}")
    public boolean addPersonToAuftrag(@PathVariable("personID") int pID,
                                      @PathVariable("auftragID") int aID,
                                      @PathVariable("senderID") int sID){
        Optional<User> benutzer = user_repo.findById(pID);
        Optional<Assignment> auftrag = assignment_repo.findById(aID);
        Optional<User> sender = user_repo.findById(sID);

/*        if(!auftrag.get().getApproved() || auftrag.get().getApproved() == null){
            return false;
        }*/

        auftrag.get().getPersonal().add(benutzer.get());
        benutzer.get().getAssignments().add(auftrag.get());

        assignment_repo.save(auftrag.get());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String tMail = benutzer.get().getEmail();
        String subject = "Sie wurden zu einem neuen Auftrag hinzugefügt";
        String text = "Guten Tag " + benutzer.get().getFirstname() + " " + benutzer.get().getLastname() + "! \n" +
                      "Sie wurden von " + sender.get().getFirstname() + " " + sender.get().getLastname() + " zu einem neuen Auftrag hinzugefügt. \n" +
                      "Der Titel des Auftrags lautet: " + auftrag.get().getAssignmentDescription() + ".\n" +
                      "Der Auftrag sollte von " + format.format(auftrag.get().getStart()) + " bis am " + format.format(auftrag.get().getEnd()) + " erledigt werden. \n" +
                      "Wir wünschen Ihnen ein gutes Gelingen. \n" +
                      "Ihr Wirtschaftshof Aschachtal!";
        System.out.println(text);
        emailSenderService.sendEmail(tMail, text, subject);
        return true;
    }

    @DeleteMapping(path = "/deleteAssignment/{assignmentId}")
    public void deleteAuftrag(@PathVariable("assignmentId") int assignmentId){
        assignment_repo.deleteById(assignmentId);
    }

    @PutMapping(path = "/updateAssignment/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Assignment editEntry(@PathVariable("id") int id, @RequestBody AssignmentDto newAssignment){
        Optional<Assignment> oldAuftrag = assignment_repo.findById(id);

        Optional<Municipal> municipal = municipal_repo.findById(newAssignment.getMunicipalDto());
        Optional<Status> status = status_repo.findById(newAssignment.getStatusDto());
        Optional<Costcenter> costCenter = costcenter_repo.findById(newAssignment.getCostCenterDto());
        List<User> personal = (List<User>)user_repo.findAllById(newAssignment.getPersonal());


        oldAuftrag.get().setStatus(status.get());
        oldAuftrag.get().setMunicipal(municipal.get());
        oldAuftrag.get().setCostcenter(costCenter.get());
        oldAuftrag.get().setAssignmentDescription(newAssignment.getAssignmentDescription());
        oldAuftrag.get().setEmail(newAssignment.getEmail());
        oldAuftrag.get().setEnd(newAssignment.getEnd());
        oldAuftrag.get().setProgress(newAssignment.getProgress());
        oldAuftrag.get().setLink(newAssignment.getLink());
        oldAuftrag.get().setPersonal(personal);
        oldAuftrag.get().setStart(newAssignment.getStart());

        return assignment_repo.save(oldAuftrag.get());
    }

    @GetMapping(path = "/approveAssignment/{id}")
    public Assignment approveEntry(@PathVariable("id") int id){
        Optional<Assignment> a = assignment_repo.findById(id);
        a.get().setApproved(true);
        return assignment_repo.save(a.get());
    }

    @GetMapping(path = "/getAll")
    public List<Assignment> getAllEntries(){
        List<Assignment> assignmentList = (List<Assignment>) assignment_repo.findAll();
        return assignmentList;
    }

    @GetMapping(path = "/getAllNotApproved")
    public List<Assignment> getAllNotApproved(){
        List<Assignment> assignmentList = assignment_repo.findByApprovedIsFalse();
        return assignmentList;
    }
}
