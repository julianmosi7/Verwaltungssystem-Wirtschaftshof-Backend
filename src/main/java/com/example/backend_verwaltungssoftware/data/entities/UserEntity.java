package com.example.backend_verwaltungssoftware.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String username;
    //@JsonIgnore
    private String password;
    String firstname;
    String lastname;
    String email;

    Date birthdate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    RoleEntity role;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_Licence",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "licence_id")})
    List<LicenceEntity> licence;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_Holiday",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "holiday_id")})
    List<HolidayEntity> holiday;
    @JsonIgnoreProperties("personal")
    @ManyToMany(mappedBy = "personal")
    List<AssignmentEntity> assignment;
}
