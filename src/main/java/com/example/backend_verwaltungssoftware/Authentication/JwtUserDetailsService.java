package com.example.backend_verwaltungssoftware.Authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String usrName) throws UsernameNotFoundException {
        if(usrName.equals("selimosi")){
            System.out.println("User found");
            return new User("selimosi", "$2a$10$PNeJN3ygtkIyqSzRGU7rge4Ll6jTgu2Xgzv4JJmbMwJ.Ux0RhElJ6", new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User " + usrName + " not found!");
        }
    }
}
