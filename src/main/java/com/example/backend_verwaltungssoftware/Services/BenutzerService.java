package com.example.backend_verwaltungssoftware.Services;

import com.example.backend_verwaltungssoftware.DTOs.BenutzerDto;
import com.example.backend_verwaltungssoftware.Entities.Benutzer;
import com.example.backend_verwaltungssoftware.Entities.Rolle;
import com.example.backend_verwaltungssoftware.Repositories.Benutzer_Repo;
import com.example.backend_verwaltungssoftware.Services.interfaces.BenutzerServiceInterface;
import com.example.backend_verwaltungssoftware.Services.interfaces.RolleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BenutzerService implements UserDetailsService, BenutzerServiceInterface {

    @Autowired
    private RolleServiceInterface rolleService;

    @Autowired
    Benutzer_Repo benutzer_repo;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("benutzerService::loadUserByUsername");
        Benutzer benutzer = benutzer_repo.findByUsername(username);

        if(benutzer == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(benutzer.getUsername(), benutzer.getPasswort(), getAuthority(benutzer));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Benutzer benutzer){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + benutzer.getRolle()));
        return authorities;
    }

    public List<Benutzer> findAll(){
        List<Benutzer> list = new ArrayList<>();
        benutzer_repo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Benutzer findOne(String username){
        return benutzer_repo.findByUsername(username);
    }

    @Override
    public Benutzer save(BenutzerDto benutzerDto){
        Benutzer benutzer = benutzerDto.getUserFromDto();
        benutzer.setPasswort(bcryptEncoder.encode(benutzerDto.getPassword()));

        Rolle rolle = rolleService.findByName("USER");
        Set<Rolle> roleSet = new HashSet<>();
        roleSet.add(rolle);

        if(benutzer.getRolle().equals("ADMIN")){
            rolle = rolleService.findByName("ADMIN");
            roleSet.add(rolle);
        }

        benutzer.setRolle(rolle);
        return benutzer_repo.save(benutzer);
    }
}
