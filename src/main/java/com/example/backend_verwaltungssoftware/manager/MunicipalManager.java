package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.Municipal_Repo;
import com.example.backend_verwaltungssoftware.data.entities.MunicipalEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Municipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MunicipalManager {
    @Autowired
    private Municipal_Repo municipal_repo;

    public List<Municipal> getAll(){
        List<Municipal> municipals = new ArrayList<>();

        for (MunicipalEntity municipalEntity :
                municipal_repo.findAll()) {
            municipals.add(convertMunicipalEntityToMunicipal(municipalEntity));
        }
        return municipals;
    }

    public Municipal findById(int id){
        Optional<MunicipalEntity> municipalEntity = municipal_repo.findById(id);
        return convertMunicipalEntityToMunicipal(municipalEntity.get());
    }

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
