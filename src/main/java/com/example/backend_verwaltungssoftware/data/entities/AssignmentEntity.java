package com.example.backend_verwaltungssoftware.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "assignment")
public class AssignmentEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assignmentId;

    @ManyToOne
    @JoinColumn(name = "municipalId")
    private MunicipalEntity municipal;
    @OneToOne
    CostcenterEntity costCenter;
    String email;
    String assignmentLink;
    String assignmentDescription;

    @JsonIgnoreProperties("assignments")
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "assignment_Personal",
            joinColumns = {@JoinColumn(name = "assignment_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<UserEntity> personal;
    Date start;
    Date end;
    double progress;
    @ManyToOne
    @JoinColumn(name = "statusId")
    StatusEntity status;
    Boolean approved;
}
