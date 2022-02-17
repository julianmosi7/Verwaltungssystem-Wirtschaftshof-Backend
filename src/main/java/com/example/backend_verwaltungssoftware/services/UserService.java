package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.data.dtos.UserDto;
import com.example.backend_verwaltungssoftware.data.entities.RoleEntity;
import com.example.backend_verwaltungssoftware.data.entities.UserEntity;
import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.*;
import com.example.backend_verwaltungssoftware.data.resources.*;
import com.example.backend_verwaltungssoftware.manager.HolidayManager;
import com.example.backend_verwaltungssoftware.manager.RoleManager;
import com.example.backend_verwaltungssoftware.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserManager userManager;
    @Autowired
    RoleManager roleManager;
    @Autowired
    HolidayManager holidayManager;

    public List<UserResource> getAll(){
        List<UserResource> result = new ArrayList<>();
        for (User user :
                userManager.getAll()) {
            result.add(convertUserToUserResource(user));
        }
        return result;
    }

    public UserResource getUserByUsername(String username){
        return convertUserToUserResource(userManager.getUserByUsername(username));
    }

    public UserResource addUser(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePassword);

        //or find role by name, instead of id
        if(userDto.getRoleId() == -1){
            userDto.setRoleId(0);
        }

        User user = convertUserDtoToUser(userDto, -1);

        /*if(userDto.getRole() != null){
            Optional<RoleEntity> r = role_repo.findById(userEntity.getRole().getRoleId());
            userEntity.setRole(r.get());
        }*/

        //TODO: add property licences to user
        /*if(user.getLicences() != null){
            for (int i = 0; i < user.getLicences().size(); i++) {
                Optional<Licence> f = licence_repo.findById(user.getLicences().get(i).getFührerschein_id());
                user.getLicences().add(f.get());
            }
        }*/

        return convertUserToUserResource(userManager.addUser(user));
    }

    public UserResource editUser(int userId, UserDto newUserDto){
        User userToEdit = userManager.findById(userId);
        userToEdit = convertUserDtoToUser(newUserDto, userToEdit.getId());
        return convertUserToUserResource(userManager.addUser(userToEdit));
    }

    public UserResource deleteUser(int userId){
        return convertUserToUserResource(userManager.deleteUser(userId));
    }

    public List<UserCalenderResource> getAllCal(){
        List<UserCalenderResource> result = new ArrayList<>();

        for (User user :
                userManager.getAll()) {
            result.add(new UserCalenderResource(user.getId(), user.getLastname()));
        }
        return result;
    }

    public UserResource addHolidayToUser(int userId, int holidayId){
        User user = userManager.findById(userId);
        Holiday holiday = holidayManager.findById(holidayId);

        user.getHolidays().add(holiday);

        return convertUserToUserResource(userManager.addUser(user));
    }

    private UserResource convertUserToUserResource(User user){
        UserResource result = new UserResource();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setFirstname(user.getFirstname());
        result.setLastname(user.getLastname());
        result.setEmail(user.getEmail());
        result.setBirthdate(user.getBirthdate());

        if(user.getRole()!=null){
            RoleResource roleResource = new RoleResource();
            roleResource.setId(user.getRole().getId());
            roleResource.setName(user.getRole().getName());
            result.setRole(roleResource);
        }

        List<LicenceResource> licenceResources = new ArrayList<>();
        for (Licence licence :
                user.getLicences()) {
            LicenceResource licenceResource = new LicenceResource();
            licenceResource.setId(licence.getId());
            licenceResource.setDescription(licence.getDescription());
            licenceResources.add(licenceResource);
        }
        result.setLicences(licenceResources);

        List<HolidayResource> holidayResources = new ArrayList<>();
        for (Holiday holiday :
                user.getHolidays()) {
            HolidayResource holidayResource = new HolidayResource();
            holidayResource.setId(holiday.getId());
            holidayResource.setBegin(holiday.getBegin());
            holidayResource.setEnd(holiday.getEnd());
            holidayResource.setReason(holiday.getReason());
        }
        result.setHolidays(holidayResources);

        List<AssignmentResource> assignmentResources = new ArrayList<>();
        for (Assignment assignment :
                user.getAssignments()) {
            AssignmentResource assignmentResource = new AssignmentResource();
            assignmentResource.setId(assignment.getId());

            MunicipalResource municipalResource = new MunicipalResource();
            municipalResource.setId(assignment.getMunicipal().getId());
            municipalResource.setName(assignment.getMunicipal().getName());
            assignmentResource.setMunicipal(municipalResource);

            CostCenterResource costCenterResource = new CostCenterResource();
            costCenterResource.setId(assignment.getCostCenter().getId());
            costCenterResource.setCost_id(assignment.getCostCenter().getCost_id());
            costCenterResource.setDescription(assignment.getCostCenter().getDescription());
            costCenterResource.setCategory(assignment.getCostCenter().getCategory());
            assignmentResource.setCostCenter(costCenterResource);

            assignmentResource.setEmail(assignment.getEmail());
            assignmentResource.setAssignmentLink(assignment.getAssignmentLink());
            assignmentResource.setAssignmentDescription(assignment.getAssignmentDescription());

            assignmentResource.setStart(assignment.getStart());
            assignmentResource.setEnd(assignment.getEnd());
            assignmentResource.setProgress(assignment.getProgress());

            StatusResource statusResource = new StatusResource();
            statusResource.setId(assignment.getStatus().getId());
            statusResource.setDescription(assignment.getStatus().getDescription());
            assignmentResource.setStatus(statusResource);

            assignmentResource.setApproved(assignment.getApproved());
            assignmentResources.add(assignmentResource);
        }
        result.setAssignments(assignmentResources);
        return result;
    }

    private User convertUserDtoToUser(UserDto userDto, int oldUserId){
        //if assignments, holidays, ... should be added with the user, change the dto (see assignmentsService)
        //or make this look like this on every method
        User user = userDto.getUserFromUserDto();
        user.setRole(roleManager.findById(userDto.getRoleId()));
        user.setId(oldUserId);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("benutzerService::loadUserByUsername");

        User user = userManager.loadUserByUsername(username);

        System.out.println(user.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    /*@Override
    public UserEntity save(UserDto userDto){
        UserEntity userEntity = userDto.getUserEntityFromDto();
        userEntity.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        RoleEntity roleEntity = rolleService.findByName("USER");

        if(userEntity.getRole().equals("USER")){
            userEntity.setRole(roleEntity);
        }

        if(userEntity.getRole().equals("ADMIN")){
            roleEntity = rolleService.findByName("ADMIN");
            userEntity.setRole(roleEntity);
        }

        userEntity.setRole(roleEntity);
        return user_repo.save(userEntity);
    }*/
}
