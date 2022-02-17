package com.example.backend_verwaltungssoftware.data.dtos;

import com.example.backend_verwaltungssoftware.data.entities.UserEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Role;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.User;
import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private int roleId;

    public UserEntity getUserEntityFromDto(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setFirstname(firstname);
        userEntity.setLastname(lastname);
        userEntity.setEmail(email);
        userEntity.setBirthdate(birthdate);
        return userEntity;
    }

    public User getUserFromUserDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBirthdate(birthdate);
        return user;
    }
}
