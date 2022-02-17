package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.data.dtos.MunicipalDto;
import com.example.backend_verwaltungssoftware.data.entities.MunicipalEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Municipal;
import com.example.backend_verwaltungssoftware.data.resources.MunicipalResource;
import com.example.backend_verwaltungssoftware.manager.MunicipalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MunicipalService {
    @Autowired
    MunicipalManager municipalManager;

    public List<MunicipalResource> getAll(){
        List<MunicipalResource> result = new ArrayList<>();
        for (Municipal municipal :
                municipalManager.getAll()) {
            result.add(convertMunicipalToMunicipalResource(municipal));
        }
        return result;
    }

    private MunicipalResource convertMunicipalToMunicipalResource(Municipal municipal){
        MunicipalResource result = new MunicipalResource();
        result.setId(municipal.getId());
        result.setName(municipal.getName());
        return result;
    }

    private Municipal convertMunicipalDtoToMunicipal(MunicipalDto municipalDto){
        Municipal result = new Municipal();
        result.setId(-1);
        result.setName(municipalDto.getName());
        return result;
    }
}
