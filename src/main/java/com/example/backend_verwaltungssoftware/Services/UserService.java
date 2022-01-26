package com.example.backend_verwaltungssoftware.Services;

import com.example.backend_verwaltungssoftware.DTOs.UserDto;
import com.example.backend_verwaltungssoftware.Entities.User;
import com.example.backend_verwaltungssoftware.Entities.Role;
import com.example.backend_verwaltungssoftware.Repositories.User_Repo;
import com.example.backend_verwaltungssoftware.Services.interfaces.UserServiceInterface;
import com.example.backend_verwaltungssoftware.Services.interfaces.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService, UserServiceInterface {

    @Autowired
    private RoleServiceInterface rolleService;

    @Autowired
    User_Repo user_repo;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("benutzerService::loadUserByUsername");

        User user = user_repo.findByUsername(username);

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

    public List<User> findAll(){
        List<User> list = new ArrayList<>();
        user_repo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username){
        return user_repo.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto){
        User user = userDto.getUserFromDto();
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        Role role = rolleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(user.getRole().equals("ADMIN")){
            role = rolleService.findByName("ADMIN");
            roleSet.add(role);
        }

        user.setRole(role);
        return user_repo.save(user);
    }
}
