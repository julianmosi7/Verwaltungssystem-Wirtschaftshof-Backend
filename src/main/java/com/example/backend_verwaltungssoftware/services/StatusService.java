package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.data.dtos.StatusDto;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Status;
import com.example.backend_verwaltungssoftware.data.resources.StatusResource;
import com.example.backend_verwaltungssoftware.manager.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusService {
    @Autowired
    StatusManager statusManager;

    public List<StatusResource> getAll(){
        List<StatusResource> result = new ArrayList<>();
        for (Status status :
                statusManager.getAll()) {
            result.add(convertStatusToStatusResource(status));
        }
        return result;
    }

    private StatusResource convertStatusToStatusResource(Status status){
        StatusResource result = new StatusResource();
        result.setId(status.getId());
        result.setDescription(status.getDescription());
        return result;
    }

    private Status convertStatusDtoToStatus(StatusDto statusDto){
        Status result = new Status();
        result.setId(-1);
        result.setDescription(statusDto.getDescription());
        return result;
    }
}
