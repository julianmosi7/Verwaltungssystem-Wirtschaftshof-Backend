package com.example.backend_verwaltungssoftware.manager;

import com.example.backend_verwaltungssoftware.Repositories.Holiday_Repo;
import com.example.backend_verwaltungssoftware.data.entities.HolidayEntity;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class HolidayManager {
    @Autowired
    private Holiday_Repo holiday_repo;

    public List<Holiday> getAll(){
        List<Holiday> holidays = new ArrayList<>();

        for (HolidayEntity holidayEntity :
                holiday_repo.findAll()) {
            holidays.add(convertHolidayEntityToHoliday(holidayEntity));
        }
        return holidays;
    }

    public Holiday findById(int id){
        Optional<HolidayEntity> holidayEntity = holiday_repo.findById(id);
        return convertHolidayEntityToHoliday(holidayEntity.get());
    }

    public Holiday addHoliday(Holiday holiday){
        HolidayEntity holidayEntity = holiday_repo.save(convertHolidayToHolidayEntity(holiday));
        return convertHolidayEntityToHoliday(holidayEntity);
    }

    private Holiday convertHolidayEntityToHoliday(HolidayEntity holidayEntity){
        Holiday result = new Holiday();
        result.setId(holidayEntity.getHolidayId());
        result.setBegin(holidayEntity.getBegin());
        result.setEnd(holidayEntity.getEnd());
        result.setReason(holidayEntity.getReason());
        return result;
    }

    private HolidayEntity convertHolidayToHolidayEntity(Holiday holiday){
        HolidayEntity result = new HolidayEntity();

        if(holiday.getId() != -1){
            result.setHolidayId(holiday.getId());
        }
        result.setBegin(holiday.getBegin());
        result.setEnd(holiday.getEnd());
        result.setReason(holiday.getReason());
        return result;
    }
}
