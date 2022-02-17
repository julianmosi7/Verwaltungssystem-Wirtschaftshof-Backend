package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.data.dtos.CostCenterDto;
import com.example.backend_verwaltungssoftware.data.entities.CostcenterEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Costcenter;
import com.example.backend_verwaltungssoftware.data.resources.CostCenterResource;
import com.example.backend_verwaltungssoftware.manager.CostcenterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CostcenterService {
    @Autowired
    CostcenterManager costcenterManager;

    public List<CostCenterResource> getAll(){
        List<CostCenterResource> result = new ArrayList<>();
        for (Costcenter costcenter:
                costcenterManager.getAll()) {
            result.add(convertCostcenterToCostcenterResource(costcenter));
        }
        return result;
    }

    public CostCenterResource addCostcenter(CostCenterDto costCenterDto){
        Costcenter newCostcenter = convertCostcenterDtoToCostcenter(costCenterDto);
        return convertCostcenterToCostcenterResource(costcenterManager.addCostcenter(newCostcenter));
    }

    private CostCenterResource convertCostcenterToCostcenterResource(Costcenter costcenter){
        CostCenterResource result = new CostCenterResource();
        result.setId(costcenter.getId());
        result.setCost_id(costcenter.getCost_id());
        result.setDescription(costcenter.getDescription());
        result.setCategory(costcenter.getCategory());
        return result;
    }

    private Costcenter convertCostcenterDtoToCostcenter(CostCenterDto costCenterDto){
        Costcenter result = new Costcenter();
        result.setId(-1);
        result.setCost_id(costCenterDto.getCost_id());
        result.setDescription(costCenterDto.getDescription());
        result.setCategory(costCenterDto.getCategory());
        return result;
    }
}
