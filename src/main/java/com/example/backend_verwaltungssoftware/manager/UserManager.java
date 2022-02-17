package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.data.entities.*;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.*;
import com.example.backend_verwaltungssoftware.data.resources.UserCalenderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserManager {
    @Autowired
    private User_Repo user_repo;

    public List<User> getAll(){
        List<User> users = new ArrayList<>();

        for (UserEntity userEntity :
                user_repo.findAll()) {
            users.add(convertUserEntityToUser(userEntity));
        }
        return users;
    }

    public List<User> findAllById(List<Integer> ids){
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity:
                user_repo.findAllById(ids)) {
            users.add(convertUserEntityToUser(userEntity));
        }
        return users;
    }

    public User findById(int userId){
        if(!user_repo.findById(userId).isPresent()){
            //TODO: throw error
            return null;
        }else{
            return convertUserEntityToUser(user_repo.findById(userId).get());
        }
    }

    public User addUser(User user){
        UserEntity userEntity = user_repo.save(convertUserToUserEntity(user));
        return convertUserEntityToUser(userEntity);
    }

    public User deleteUser(int userId){
        Optional<UserEntity> userEntity = user_repo.findById(userId);

        if(userEntity.isPresent()){
            user_repo.delete(userEntity.get());
            return convertUserEntityToUser(userEntity.get());
        }else{
            //TODO: throw error
            return null;
        }
    }

    public User getUserByUsername(String username){
        return convertUserEntityToUser(user_repo.findByUsername(username));
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        return convertUserEntityToUser(user_repo.findByUsername(username));
    }

    private User convertUserEntityToUser(UserEntity userEntity){
        User result = new User();
        result.setId(userEntity.getUserId());
        result.setUsername(userEntity.getUsername());
        result.setPassword(userEntity.getPassword());
        result.setFirstname(userEntity.getFirstname());
        result.setLastname(userEntity.getLastname());
        result.setEmail(userEntity.getEmail());
        result.setBirthdate(userEntity.getBirthdate());

        if(userEntity.getRole()!=null){
            Role role = new Role();
            role.setId(userEntity.getRole().getRoleId());
            role.setName(userEntity.getRole().getName());
            result.setRole(role);
        }


        List<Licence> licences = new ArrayList<>();
        for (LicenceEntity licenceEntity :
                userEntity.getLicence()) {
            Licence licence = new Licence();
            licence.setId(licenceEntity.getLicenceId());
            licence.setDescription(licenceEntity.getDescription());
            licences.add(licence);
        }
        result.setLicences(licences);

        List<Holiday> holidays = new ArrayList<>();
        for (HolidayEntity holidayEntity :
                userEntity.getHoliday()) {
            Holiday holiday = new Holiday();
            holiday.setId(holidayEntity.getHolidayId());
            holiday.setBegin(holidayEntity.getBegin());
            holiday.setEnd(holidayEntity.getEnd());
            holiday.setReason(holidayEntity.getReason());
            holidays.add(holiday);
        }
        result.setHolidays(holidays);

        List<Assignment> assignments = new ArrayList<>();
        for (AssignmentEntity assignmentEntity :
                userEntity.getAssignment()) {
            Assignment assignment = new Assignment();
            assignment.setId(assignmentEntity.getAssignmentId());

            Municipal municipal = new Municipal();
            municipal.setId(assignmentEntity.getMunicipal().getMunicipalId());
            municipal.setName(assignmentEntity.getMunicipal().getName());
            assignment.setMunicipal(municipal);

            Costcenter costcenter = new Costcenter();
            costcenter.setId(assignmentEntity.getCostCenter().getCostCenterId());
            costcenter.setCost_id(assignmentEntity.getCostCenter().getCost_id());
            costcenter.setDescription(assignmentEntity.getCostCenter().getDescription());
            costcenter.setCategory(assignmentEntity.getCostCenter().getCategory());
            assignment.setCostCenter(costcenter);

            assignment.setEmail(assignmentEntity.getEmail());
            assignment.setAssignmentLink(assignmentEntity.getAssignmentLink());
            assignment.setAssignmentDescription(assignmentEntity.getAssignmentDescription());

            assignment.setStart(assignmentEntity.getStart());
            assignment.setEnd(assignmentEntity.getEnd());
            assignment.setProgress(assignmentEntity.getProgress());

            Status status = new Status();
            status.setId(assignmentEntity.getStatus().getStatusId());
            status.setDescription(assignmentEntity.getStatus().getDescription());
            assignment.setStatus(status);

            assignment.setApproved(assignmentEntity.getApproved());
            assignments.add(assignment);
        }
        result.setAssignments(assignments);
        return result;
    }

    private UserEntity convertUserToUserEntity(User user){
        UserEntity result = new UserEntity();

        if(user.getId() != -1){
            result.setUserId(user.getId());
        }

        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setFirstname(user.getFirstname());
        result.setLastname(user.getLastname());
        result.setEmail(user.getEmail());
        result.setBirthdate(user.getBirthdate());

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(user.getRole().getId());
        roleEntity.setName(user.getRole().getName());
        result.setRole(roleEntity);

        List<LicenceEntity> licenceEntities = new ArrayList<>();
        for (Licence licence :
                user.getLicences()) {
            LicenceEntity licenceEntity = new LicenceEntity();
            licenceEntity.setLicenceId(licence.getId());
            licenceEntity.setDescription(licence.getDescription());
            licenceEntities.add(licenceEntity);
        }
        result.setLicence(licenceEntities);

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
        result.setHoliday(holidayEntities);

        List<AssignmentEntity> assignmentEntities = new ArrayList<>();
        for (Assignment assignment :
                user.getAssignments()) {
            AssignmentEntity assignmentEntity = new AssignmentEntity();
            assignmentEntity.setAssignmentId(assignment.getId());

            MunicipalEntity municipalEntity = new MunicipalEntity();
            municipalEntity.setMunicipalId(assignment.getMunicipal().getId());
            municipalEntity.setName(assignment.getMunicipal().getName());
            assignmentEntity.setMunicipal(municipalEntity);

            CostcenterEntity costcenterEntity = new CostcenterEntity();
            costcenterEntity.setCostCenterId(assignment.getCostCenter().getId());
            costcenterEntity.setCost_id(assignment.getCostCenter().getCost_id());
            costcenterEntity.setDescription(assignment.getCostCenter().getDescription());
            costcenterEntity.setCategory(assignment.getCostCenter().getCategory());
            assignmentEntity.setCostCenter(costcenterEntity);

            assignmentEntity.setEmail(assignment.getEmail());
            assignmentEntity.setAssignmentLink(assignment.getAssignmentLink());
            assignmentEntity.setAssignmentDescription(assignment.getAssignmentDescription());
            assignmentEntity.setStart(assignment.getStart());
            assignmentEntity.setEnd(assignment.getEnd());
            assignmentEntity.setProgress(assignment.getProgress());

            StatusEntity statusEntity = new StatusEntity();
            statusEntity.setStatusId(assignment.getStatus().getId());
            statusEntity.setDescription(assignment.getStatus().getDescription());
            assignmentEntity.setStatus(statusEntity);

            assignmentEntity.setApproved(assignment.getApproved());
            assignmentEntities.add(assignmentEntity);
        }
        result.setAssignment(assignmentEntities);
        return result;
    }
}
