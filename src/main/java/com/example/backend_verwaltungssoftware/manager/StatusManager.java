package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.Status_Repo;
import com.example.backend_verwaltungssoftware.data.entities.StatusEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StatusManager {
    @Autowired
    private Status_Repo status_repo;

    public List<Status> getAll(){
        List<Status> status = new ArrayList<>();

        for (StatusEntity statusEntity :
                status_repo.findAll()) {
            status.add(convertStatusEntityToStatus(statusEntity));
        }
        return status;
    }

    public Status findById(int id){
        Optional<StatusEntity> statusEntity = status_repo.findById(id);
        return convertStatusEntityToStatus(statusEntity.get());
    }

    private Status convertStatusEntityToStatus(StatusEntity statusEntity){
        Status result = new Status();
        result.setId(statusEntity.getStatusId());
        result.setDescription(statusEntity.getDescription());
        return result;
    }

    private StatusEntity convertStatusToStatusEntity(Status status){
        StatusEntity result = new StatusEntity();

        if(status.getId() != -1){
            result.setStatusId(status.getId());
        }
        result.setDescription(status.getDescription());
        return result;
    }
}
