package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.Entities.Benutzer;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface Benutzer_Repo extends CrudRepository<Benutzer, Integer> {
    Benutzer findByUsername(String username);
}
