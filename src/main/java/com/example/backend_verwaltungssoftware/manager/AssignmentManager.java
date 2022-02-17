package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.*;
import com.example.backend_verwaltungssoftware.data.entities.*;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.*;
import com.example.backend_verwaltungssoftware.data.resources.MunicipalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentManager {
    @Autowired
    private Assignment_Repo assignment_repo;

    public List<Assignment> getAll(){
        List<Assignment> assignments = new ArrayList<>();

        for (AssignmentEntity assignmentEntity:
                assignment_repo.findAll()) {
            assignments.add(convertAssignmentEntityToAssignment(assignmentEntity));
        }
        return assignments;
    }

    public List<Assignment> getAllNotApproved(){
        List<Assignment> assignmentsNotApproved = new ArrayList<>();

        for (AssignmentEntity assignmentEntity :
                assignment_repo.findByApprovedIsFalse()) {
            assignmentsNotApproved.add(convertAssignmentEntityToAssignment(assignmentEntity));
        }
        return assignmentsNotApproved;
    }

    public List<Assignment> getAllApproved(){
        List<Assignment> assignmentsApproved = new ArrayList<>();

        for (AssignmentEntity assignmentEntity :
                assignment_repo.findByApprovedIsTrue()) {
            assignmentsApproved.add(convertAssignmentEntityToAssignment(assignmentEntity));
        }
        return assignmentsApproved;
    }
    
    public List<Assignment> getAssignmentsOfUser(int userId){
        List<Assignment> assignments = new ArrayList<>();

        for (AssignmentEntity assignmentEntity :
                assignment_repo.findByPersonal(userId)) {
            assignments.add(convertAssignmentEntityToAssignment(assignmentEntity));
        }
        return assignments;
    }

    public Assignment addAssignment(Assignment assignment){
        AssignmentEntity assignmentEntity = assignment_repo.save(convertAssignmentToAssignmentEntity(assignment));
        return convertAssignmentEntityToAssignment(assignmentEntity);
    }

    public Assignment deleteAssignment(int assignmentId){
        Optional<AssignmentEntity> assignmentEntity = assignment_repo.findById(assignmentId);

        if(assignmentEntity.isPresent()){
            assignment_repo.delete(assignmentEntity.get());
            return convertAssignmentEntityToAssignment(assignmentEntity.get());
        }else{
            //TODO: throw error
            return null;
        }
    }

    public Assignment approveAssignment(int assignmentId){
        if(!assignment_repo.findById(assignmentId).isPresent()){
            //TODO: throw error
            return null;
        }else{
            Assignment assignmentToApprove = convertAssignmentEntityToAssignment(assignment_repo.findById(assignmentId).get());
            assignmentToApprove.setApproved(true);
            AssignmentEntity assignmentEntity = assignment_repo.save(convertAssignmentToAssignmentEntity(assignmentToApprove));
            return convertAssignmentEntityToAssignment(assignmentEntity);
        }
    }

    public Assignment findById(int assignmentId){
        if(!assignment_repo.findById(assignmentId).isPresent()){
            //TODO: throw error
            return null;
        }else{
            return convertAssignmentEntityToAssignment(assignment_repo.findById(assignmentId).get());
        }
    }

    private Assignment convertAssignmentEntityToAssignment(AssignmentEntity assignmentEntity){
        Assignment result = new Assignment();
        result.setId(assignmentEntity.getAssignmentId());

        Municipal municipal = new Municipal();
        municipal.setId(assignmentEntity.getMunicipal().getMunicipalId());
        municipal.setName(assignmentEntity.getMunicipal().getName());
        result.setMunicipal(municipal);

        Costcenter costcenter = new Costcenter();
        costcenter.setId(assignmentEntity.getCostCenter().getCostCenterId());
        costcenter.setCost_id(assignmentEntity.getCostCenter().getCost_id());
        costcenter.setDescription(assignmentEntity.getCostCenter().getDescription());
        costcenter.setCategory(assignmentEntity.getCostCenter().getCategory());
        result.setCostCenter(costcenter);

        result.setEmail(assignmentEntity.getEmail());
        result.setAssignmentLink(assignmentEntity.getAssignmentLink());
        result.setAssignmentDescription(assignmentEntity.getAssignmentDescription());

        List<User> personal = new ArrayList<>();
        for (UserEntity userEntity:
             assignmentEntity.getPersonal()) {
            User user = new User();
            user.setId(userEntity.getUserId());
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setFirstname(userEntity.getFirstname());
            user.setLastname(userEntity.getLastname());
            user.setEmail(userEntity.getEmail());
            user.setBirthdate(userEntity.getBirthdate());

            Role role = new Role();
            role.setId(userEntity.getRole().getRoleId());
            role.setName(userEntity.getRole().getName());
            user.setRole(role);

            List<Licence> licences = new ArrayList<>();
            for (LicenceEntity licenceEntity:
                 userEntity.getLicence()) {
                Licence licence = new Licence();
                licence.setId(licenceEntity.getLicenceId());
                licence.setDescription(licenceEntity.getDescription());
                licences.add(licence);
            }
            user.setLicences(licences);

            List<Holiday> holidays = new ArrayList<>();
            for (HolidayEntity holidayEnitity:
                 userEntity.getHoliday()) {
                Holiday holiday = new Holiday();
                holiday.setId(holidayEnitity.getHolidayId());
                holiday.setBegin(holidayEnitity.getBegin());
                holiday.setEnd(holidayEnitity.getEnd());
                holiday.setReason(holidayEnitity.getReason());
                holidays.add(holiday);
            }
            user.setHolidays(holidays);
            personal.add(user);
        }
        result.setPersonal(personal);

        result.setStart(assignmentEntity.getStart());
        result.setEnd(assignmentEntity.getEnd());
        result.setProgress(assignmentEntity.getProgress());

        Status status = new Status();
        status.setId(assignmentEntity.getStatus().getStatusId());
        status.setDescription(assignmentEntity.getStatus().getDescription());
        result.setStatus(status);

        result.setApproved(assignmentEntity.getApproved());
        return result;
    }

    private AssignmentEntity convertAssignmentToAssignmentEntity(Assignment assignment){
        AssignmentEntity result = new AssignmentEntity();

        if(result.getAssignmentId() != -1){
            result.setAssignmentId(assignment.getId());
        }

        MunicipalEntity municipalEntity = new MunicipalEntity();
        municipalEntity.setMunicipalId(assignment.getMunicipal().getId());
        municipalEntity.setName(assignment.getMunicipal().getName());
        result.setMunicipal(municipalEntity);

        CostcenterEntity costcenterEntity = new CostcenterEntity();
        costcenterEntity.setCostCenterId(assignment.getCostCenter().getId());
        costcenterEntity.setCost_id(assignment.getCostCenter().getCost_id());
        costcenterEntity.setDescription(assignment.getCostCenter().getDescription());
        costcenterEntity.setCategory(assignment.getCostCenter().getCategory());
        result.setCostCenter(costcenterEntity);

        result.setEmail(assignment.getEmail());
        result.setAssignmentLink(assignment.getAssignmentLink());
        result.setAssignmentDescription(assignment.getAssignmentDescription());

        List<UserEntity> personalEntities = new ArrayList<>();
        for (User user:
             assignment.getPersonal()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(user.getId());
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(user.getPassword());
            userEntity.setFirstname(user.getFirstname());
            userEntity.setLastname(userEntity.getLastname());
            userEntity.setEmail(userEntity.getEmail());
            user.setBirthdate(userEntity.getBirthdate());

            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRoleId(user.getRole().getId());
            roleEntity.setName(user.getRole().getName());
            userEntity.setRole(roleEntity);

            List<LicenceEntity> licenceEntities = new ArrayList<>();
            for (Licence licence:
                 user.getLicences()) {
                LicenceEntity licenceEntity = new LicenceEntity();
                licenceEntity.setLicenceId(licence.getId());
                licenceEntity.setDescription(licence.getDescription());
                licenceEntities.add(licenceEntity);
            }
            userEntity.setLicence(licenceEntities);

            List<HolidayEntity> holidayEntities = new ArrayList<>();
            for (Holiday holiday :
                    user.getHolidays()) {
                HolidayEntity holidayEntity = new HolidayEntity();
                holidayEntity.setHolidayId(holiday.getId());
                holidayEntity.setBegin(holiday.getBegin());
                holidayEntity.setEnd(holiday.getEnd());
                holidayEntity.setReason(holiday.getReason());
                holidayEntities.add(holidayEntity);
            }
            userEntity.setHoliday(holidayEntities);
            personalEntities.add(userEntity);
        }
        result.setPersonal(personalEntities);

        result.setStart(assignment.getStart());
        result.setEnd(assignment.getEnd());
        result.setProgress(assignment.getProgress());

        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setStatusId(assignment.getStatus().getId());
        statusEntity.setDescription(assignment.getStatus().getDescription());
        result.setStatus(statusEntity);

        result.setApproved(assignment.getApproved());
        return result;
    }

}
