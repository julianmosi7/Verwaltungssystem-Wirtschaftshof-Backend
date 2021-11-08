package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.Entities.Gemeinde;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rolle_Repo extends CrudRepository<Gemeinde, Integer> {
}
