package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.data.entities.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Status_Repo extends CrudRepository<StatusEntity, Integer> {
}
