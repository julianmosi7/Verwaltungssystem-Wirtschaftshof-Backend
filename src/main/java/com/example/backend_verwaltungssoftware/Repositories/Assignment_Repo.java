package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.data.entities.AssignmentEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Assignment_Repo extends CrudRepository<AssignmentEntity, Integer> {
    List<AssignmentEntity> findByApprovedIsFalse();
    List<AssignmentEntity> findByApprovedIsTrue();
    List<AssignmentEntity> findByPersonal(int userId);
}
