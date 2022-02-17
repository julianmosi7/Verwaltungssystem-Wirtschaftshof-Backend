package com.example.backend_verwaltungssoftware.Repositories;

import com.example.backend_verwaltungssoftware.data.entities.HolidayEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Holiday_Repo extends CrudRepository<HolidayEntity, Integer> {
}
