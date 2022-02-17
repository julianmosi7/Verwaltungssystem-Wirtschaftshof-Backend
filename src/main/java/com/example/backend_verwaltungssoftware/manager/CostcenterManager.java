package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.Costcenter_repo;
import com.example.backend_verwaltungssoftware.data.entities.CostcenterEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Costcenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CostcenterManager {
    @Autowired
    private Costcenter_repo costcenter_repo;

    public List<Costcenter> getAll(){
        List<Costcenter> costcenters = new ArrayList<>();

        for (CostcenterEntity costcenterEntity:
                costcenter_repo.findAll()) {
            costcenters.add(convertCostcenterEntitytoCostcenter(costcenterEntity));
        }
        return costcenters;
    }

    public Costcenter addCostcenter(Costcenter costcenter){
        CostcenterEntity costcenterEntity = costcenter_repo.save(convertCostcenterToCostcenterEntity(costcenter));
        return convertCostcenterEntitytoCostcenter(costcenterEntity);
    }

    public Costcenter findById(int id){
        Optional<CostcenterEntity> costcenterEntity = costcenter_repo.findById(id);
        return convertCostcenterEntitytoCostcenter(costcenterEntity.get());
    }

    public CostcenterEntity convertCostcenterToCostcenterEntity(Costcenter costcenter){
        CostcenterEntity result = new CostcenterEntity();

        if(costcenter.getId() != -1){
            result.setCostCenterId(costcenter.getId());
        }
        result.setCost_id(costcenter.getCost_id());
        result.setDescription(costcenter.getDescription());
        result.setCategory(costcenter.getCategory());
        return result;
    }

    public Costcenter convertCostcenterEntitytoCostcenter(CostcenterEntity costcenterEntity){
        Costcenter result = new Costcenter();

        result.setId(costcenterEntity.getCostCenterId());
        result.setCost_id(costcenterEntity.getCost_id());
        result.setDescription(costcenterEntity.getDescription());
        result.setCategory(costcenterEntity.getCategory());
        return result;
    }
}
