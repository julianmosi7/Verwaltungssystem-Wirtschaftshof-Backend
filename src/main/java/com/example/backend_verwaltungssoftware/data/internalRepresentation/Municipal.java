package com.example.backend_verwaltungssoftware.data.internalRepresentation;

import com.example.backend_verwaltungssoftware.data.entities.MunicipalEntity;
import lombok.Data;

@Data
public class Municipal {
    private int id;
    String name;

    public Municipal convertMunicipalEntityToMunicipal(MunicipalEntity municipalEntity){
        Municipal result = new Municipal();
        result.setId(municipalEntity.getMunicipalId());
        result.setName(municipalEntity.getName());
        return result;
    }

    public MunicipalEntity convertMunicipalToMunicipalEntity(Municipal municipal){
        MunicipalEntity result = new MunicipalEntity();

        if(municipal.getId() != -1){
            result.setMunicipalId(municipal.getId());
        }
        result.setName(municipal.getName());
        return result;
    }
}
