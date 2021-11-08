package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.Entities.Führerschein;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Führerschein_Repo extends CrudRepository<Führerschein, Integer> {
}
