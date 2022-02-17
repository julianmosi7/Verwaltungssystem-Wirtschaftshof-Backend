package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.data.entities.*;
import com.example.backend_verwaltungssoftware.Mail.EmailSenderService;
import com.example.backend_verwaltungssoftware.Repositories.*;
import com.example.backend_verwaltungssoftware.data.dtos.AssignmentDto;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Assignment;
import com.example.backend_verwaltungssoftware.data.resources.AssignmentResource;
import com.example.backend_verwaltungssoftware.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/assignment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Assignment_Controller {

    @Autowired
    private AssignmentService assignmentService;
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

    @GetMapping(path = "/getAll")
    public HttpEntity<List<AssignmentResource>> getAll(){
        List<AssignmentResource> assignmentResources = assignmentService.getAll();
        return new HttpEntity<>(assignmentResources);
    }

    @GetMapping(path = "/getAllNotApproved")
    public HttpEntity<List<AssignmentResource>> getAllNotApproved(){
        List<AssignmentResource> assignmentResources = assignmentService.getAllNotApproved();
        return new HttpEntity<>(assignmentResources);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/getAllApproved")
    public HttpEntity<List<AssignmentResource>> getAllApproved(){
        List<AssignmentResource> assignmentResources = assignmentService.getAllApproved();
        return new HttpEntity<>(assignmentResources);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/getAssignments/{userId}")
    public HttpEntity<List<AssignmentResource>> getAssignmentsOfUser(@PathVariable int userId){
        List<AssignmentResource> assignmentResources = assignmentService.getAssignmentsOfUser(userId);
        return new HttpEntity<>(assignmentResources);
    }

    @PostMapping(path = "/newAssignment")
    public AssignmentResource addAssignment(@RequestBody AssignmentDto assignmentDto){
        return assignmentService.addAssignment(assignmentDto);
    }

    @DeleteMapping(path = "/deleteAssignment/{assignmentId}")
    public AssignmentResource deleteAssignment(@PathVariable("assignmentId") int assignmentId){
        return assignmentService.deleteAssignment(assignmentId);
    }

    @PutMapping(path = "/editAssignment/{assignmentId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AssignmentResource editAssignment(@PathVariable("assignmentId") int assignmentId, @RequestBody AssignmentDto newAssignmentDto){
        return assignmentService.editAssignment(assignmentId, newAssignmentDto);
    }

    @GetMapping(path = "/approveAssignment/{assignmentId}")
    public AssignmentResource approveEntry(@PathVariable("assignmentId") int assignmentId){
        return assignmentService.approveAssignment(assignmentId);
    }

    @GetMapping(path = "/addPersonToAssignment/{personID}/{auftragID}/{senderID}")
    public AssignmentResource addPersonToAssignment(@PathVariable("personID") int personId,
                                            @PathVariable("assignmentId") int assignmentId,
                                            @PathVariable("senderID") int senderId){
        return assignmentService.addPersonToAssignment(personId, assignmentId, senderId);
    }
}
