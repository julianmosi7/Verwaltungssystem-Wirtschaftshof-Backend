package com.example.backend_verwaltungssoftware.Controller;

import com.example.backend_verwaltungssoftware.DTOs.UserCalenderDto;
import com.example.backend_verwaltungssoftware.DTOs.UserDto;
import com.example.backend_verwaltungssoftware.Entities.Assignment;
import com.example.backend_verwaltungssoftware.Entities.User;
import com.example.backend_verwaltungssoftware.Entities.Licence;
import com.example.backend_verwaltungssoftware.Entities.Role;
import com.example.backend_verwaltungssoftware.Repositories.Assignment_Repo;
import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Licence_Repo;
import com.example.backend_verwaltungssoftware.Repositories.Role_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    User_Repo User_Repo;
    @Autowired
    Role_Repo role_repo;
    @Autowired
    Licence_Repo licence_repo;

    @PostMapping(value = "/newUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create_benutzer(@RequestBody User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        if(user.getRole() != null){
            Optional<Role> r = role_repo.findById(user.getRole().getRoleId());
            user.setRole(r.get());
        }

        /*if(user.getLicences() != null){
            for (int i = 0; i < user.getLicences().size(); i++) {
                Optional<Licence> f = licence_repo.findById(user.getLicences().get(i).getFührerschein_id());
                user.getLicences().add(f.get());
            }
        }*/

        return User_Repo.save(user);
    }

    @GetMapping(value = "/loginCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody User user){
        String encodePassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());

        List<User> allusers = (List<User>) User_Repo.findAll();

        for (int i = 0; i < allusers.size(); i++) {
            if(allusers.get(i).getEmail().equals(user.getEmail())){
                if(allusers.get(i).getPassword().equals(encodePassword)){
                    return allusers.get(i);
                }
            }
        }

        return null;
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deletePerson(@PathVariable int id){
        try {
            Optional<User> p = User_Repo.findById(id);
            User_Repo.delete(p.get());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return (List<User>) User_Repo.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllCal")
    public List<UserCalenderDto> getAllUsersCal(){
        List<UserCalenderDto> user = new ArrayList<>();
        for ( User u: User_Repo.findAll()) {
            user.add(new UserCalenderDto(u.getUserId(),u.getLastname()));
        }
        return user;

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAssignments/{id}")
    public List<Assignment> getAllUsers(@PathVariable int id){
         Optional<User> p= User_Repo.findById(id);
         return p.get().getAssignments();
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return User_Repo.findByUsername(username);
    }

    @GetMapping(path = "/editUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User editEntry(@PathVariable("id") int id, @RequestBody User newUser){
        Optional<User> oldUser = User_Repo.findById(id);

        oldUser.get().setFirstname(newUser.getFirstname());
        oldUser.get().setLastname(newUser.getLastname());
        oldUser.get().setEmail(newUser.getEmail());

        String encodePassword = Base64.getEncoder().encodeToString(newUser.getPassword().getBytes());
        oldUser.get().setPassword(encodePassword);

        oldUser.get().setBirthdate(newUser.getBirthdate());
        oldUser.get().setLicences(newUser.getLicences());

        return User_Repo.save(oldUser.get());
    }
}
