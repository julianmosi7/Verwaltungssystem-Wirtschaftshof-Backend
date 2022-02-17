package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.Mail.EmailSenderService;
import com.example.backend_verwaltungssoftware.data.dtos.AssignmentDto;
import com.example.backend_verwaltungssoftware.data.dtos.StatusDto;
import com.example.backend_verwaltungssoftware.data.entities.MunicipalEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.*;
import com.example.backend_verwaltungssoftware.data.resources.*;
import com.example.backend_verwaltungssoftware.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentService {
    @Autowired
    AssignmentManager assignmentManager;
    @Autowired
    MunicipalManager municipalManager;
    @Autowired
    CostcenterManager costcenterManager;
    @Autowired
    UserManager userManager;
    @Autowired
    StatusManager statusManager;
    @Autowired
    EmailSenderService emailSenderService;

    public List<AssignmentResource> getAll(){
        List<AssignmentResource> result = new ArrayList<>();
        for (Assignment assignment :
                assignmentManager.getAll()) {
            result.add(convertAssignmentToAssignmentResource(assignment));
        }
        return result;
    }

    public List<AssignmentResource> getAllNotApproved(){
        List<AssignmentResource> result = new ArrayList<>();
        for (Assignment assignment :
                assignmentManager.getAllNotApproved()) {
            result.add(convertAssignmentToAssignmentResource(assignment));
        }
        return result;
    }

    public List<AssignmentResource> getAllApproved(){
        List<AssignmentResource> result = new ArrayList<>();
        for (Assignment assignment :
             assignmentManager.getAllApproved()) {
            result.add(convertAssignmentToAssignmentResource(assignment));
        }
        return result;
    }

    public List<AssignmentResource> getAssignmentsOfUser(int userId){
        List<AssignmentResource> result = new ArrayList<>();
        for (Assignment assignment :
                assignmentManager.getAssignmentsOfUser(userId)) {
            result.add(convertAssignmentToAssignmentResource(assignment));
        }
        return result;
    }

    public AssignmentResource addAssignment(AssignmentDto assignmentDto){
        Assignment newAssignment = convertAssignmentDtotoAssignment(assignmentDto, -1);
        return convertAssignmentToAssignmentResource(assignmentManager.addAssignment(newAssignment));
    }

    //other approach as example
    public AssignmentResource editAssignment(int assignmentId, AssignmentDto newAssignmentDto){
        Assignment assignmentToEdit = assignmentManager.findById(assignmentId);
        assignmentToEdit = convertAssignmentDtotoAssignment(newAssignmentDto, assignmentToEdit.getId());
        return convertAssignmentToAssignmentResource(assignmentManager.addAssignment(assignmentToEdit));
    }

    public AssignmentResource deleteAssignment(int assignmentId){
        return convertAssignmentToAssignmentResource(assignmentManager.deleteAssignment(assignmentId));
    }

    public AssignmentResource approveAssignment(int assignmentId){
        return convertAssignmentToAssignmentResource(assignmentManager.approveAssignment(assignmentId));
    }

    public AssignmentResource addPersonToAssignment(int personId, int assignmentId, int senderId){
        User user = userManager.findById(personId);
        Assignment assignment = assignmentManager.findById(assignmentId);
        User sender = userManager.findById(senderId);

        assignment.getPersonal().add(user);
        user.getAssignments().add(assignment);

        Assignment returnedAssignment = assignmentManager.addAssignment(assignment);

        sendEmail(user, assignment, sender);

        return convertAssignmentToAssignmentResource(returnedAssignment);
    }

    public void sendEmail(User user, Assignment assignment, User sender){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String tMail = user.getEmail();
        String subject = "Sie wurden zu einem neuen Auftrag hinzugefügt";
        String text = "Guten Tag " + user.getFirstname() + " " + user.getLastname() + "! \n" +
                "Sie wurden von " + sender.getFirstname() + " " + sender.getLastname() +
                " zu einem neuen Auftrag hinzugefügt. \n" +
                "Der Titel des Auftrags lautet: " + assignment.getAssignmentDescription() + ".\n" +
                "Der Auftrag sollte von " + format.format(assignment.getStart()) + " bis am "
                + format.format(assignment.getEnd()) + " erledigt werden. \n" +
                "Wir wünschen Ihnen ein gutes Gelingen. \n" +
                "Ihr Wirtschaftshof Aschachtal!";
        System.out.println(text);
        emailSenderService.sendEmail(tMail, text, subject);
    }

    private AssignmentResource convertAssignmentToAssignmentResource(Assignment assignment){
        AssignmentResource result = new AssignmentResource();
        result.setId(assignment.getId());

        MunicipalResource municipalResource = new MunicipalResource();
        municipalResource.setId(assignment.getMunicipal().getId());
        municipalResource.setName(assignment.getMunicipal().getName());
        result.setMunicipal(municipalResource);

        CostCenterResource costCenterResource = new CostCenterResource();
        costCenterResource.setId(assignment.getCostCenter().getId());
        costCenterResource.setCost_id(assignment.getCostCenter().getCost_id());
        costCenterResource.setDescription(assignment.getCostCenter().getDescription());
        costCenterResource.setCategory(assignment.getCostCenter().getCategory());
        result.setCostCenter(costCenterResource);

        result.setEmail(assignment.getEmail());
        result.setAssignmentLink(assignment.getAssignmentLink());
        result.setAssignmentDescription(assignment.getAssignmentDescription());

        List<UserResource> personalResources = new ArrayList<>();
        for (User user :
                assignment.getPersonal()) {
            UserResource userResource = new UserResource();
            userResource.setId(user.getId());
            userResource.setUsername(user.getUsername());
            userResource.setPassword(user.getPassword());
            userResource.setFirstname(user.getFirstname());
            userResource.setLastname(user.getLastname());
            userResource.setEmail(user.getEmail());
            userResource.setBirthdate(user.getBirthdate());

            RoleResource roleResource = new RoleResource();
            roleResource.setId(user.getRole().getId());
            roleResource.setName(user.getRole().getName());
            userResource.setRole(roleResource);

            List<LicenceResource> licenceResources = new ArrayList<>();
            for (Licence licence :
                    user.getLicences()) {
                LicenceResource licenceResource = new LicenceResource();
                licenceResource.setId(licence.getId());
                licenceResource.setDescription(licence.getDescription());
                licenceResources.add(licenceResource);
            }
            userResource.setLicences(licenceResources);

            List<HolidayResource> holidayResources = new ArrayList<>();
            for (Holiday holiday :
                    user.getHolidays()) {
                HolidayResource holidayResource = new HolidayResource();
                holidayResource.setId(holiday.getId());
                holidayResource.setBegin(holiday.getBegin());
                holidayResource.setEnd(holiday.getEnd());
                holidayResource.setReason(holiday.getReason());
                holidayResources.add(holidayResource);
            }
            userResource.setHolidays(holidayResources);
            personalResources.add(userResource);
        }
        result.setPersonal(personalResources);

        result.setStart(assignment.getStart());
        result.setEnd(assignment.getEnd());
        result.setProgress(assignment.getProgress());

        StatusResource statusResource = new StatusResource();
        statusResource.setId(assignment.getStatus().getId());
        statusResource.setDescription(assignment.getStatus().getDescription());
        result.setStatus(statusResource);

        result.setApproved(assignment.getApproved());
        return result;
    }

    private Assignment convertAssignmentDtotoAssignment(AssignmentDto assignmentDto, int oldAssignmentId){
        Assignment result = new Assignment();

        result.setId(oldAssignmentId);
        result.setMunicipal(municipalManager.findById(assignmentDto.getMunicipalId()));
        result.setCostCenter(costcenterManager.findById(assignmentDto.getCostCenterId()));
        result.setEmail(assignmentDto.getEmail());
        result.setAssignmentLink(assignmentDto.getLink());
        result.setAssignmentDescription(assignmentDto.getAssignmentDescription());
        result.setPersonal(userManager.findAllById(assignmentDto.getPersonal()));
        result.setStart(assignmentDto.getStart());
        result.setEnd(assignmentDto.getEnd());
        result.setProgress(assignmentDto.getProgress());
        result.setStatus(statusManager.findById(assignmentDto.getStatusId()));
        result.setApproved(assignmentDto.getApproved());
        return result;
    }
}
