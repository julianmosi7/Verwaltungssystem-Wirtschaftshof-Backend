package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.Entities.Auftrag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Auftrag_Repo extends CrudRepository<Auftrag, Integer> {
}
