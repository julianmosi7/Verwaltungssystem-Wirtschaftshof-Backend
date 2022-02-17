package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.data.resources.HolidayResource;
import com.example.backend_verwaltungssoftware.data.resources.UserCalenderResource;
import com.example.backend_verwaltungssoftware.data.dtos.UserDto;
import com.example.backend_verwaltungssoftware.data.entities.AssignmentEntity;
import com.example.backend_verwaltungssoftware.data.entities.UserEntity;
import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Licence_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import com.example.backend_verwaltungssoftware.data.resources.UserResource;
import com.example.backend_verwaltungssoftware.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class User_Controller {

    @Autowired
    UserService userService;
    @Autowired
    User_Repo User_Repo;
    @Autowired
    Role_Repo role_repo;
    @Autowired
    Licence_Repo licence_repo;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAll")
    public HttpEntity<List<UserResource>> getAll(){
        List<UserResource> userResources = userService.getAll();
        return new HttpEntity<>(userResources);
    }

    @PostMapping(value = "/newUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResource addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    /*@GetMapping(value = "/loginCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity getLoginCredentials(@RequestBody UserEntity userEntity){
        String encodePassword = Base64.getEncoder().encodeToString(userEntity.getPassword().getBytes());

        List<UserEntity> allusers = (List<UserEntity>) User_Repo.findAll();

        for (int i = 0; i < allusers.size(); i++) {
            if(allusers.get(i).getEmail().equals(userEntity.getEmail())){
                if(allusers.get(i).getPassword().equals(encodePassword)){
                    return allusers.get(i);
                }
            }
        }

        return null;
    }*/

    @DeleteMapping("/deleteUser/{userId}")
    public UserResource deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllCal")
    public HttpEntity<List<UserCalenderResource>> getAllCal(){
        List<UserCalenderResource> userCalenderResources = userService.getAllCal();
        return new HttpEntity<>(userCalenderResources);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getUserByUsername/{username}")
    public UserResource getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping(path = "/editUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResource editEntry(@PathVariable("userId") int userId, @RequestBody UserDto userDto){
        return userService.editUser(userId, userDto);
    }

    @GetMapping(path = "/addHolidayToUser/{userId}/{holidayId}")
    public UserResource addHolidayToUser(@PathVariable("userId") int userId, @PathVariable("holidayId") int holidayId){
        return userService.addHolidayToUser(userId, holidayId);
    }
}
