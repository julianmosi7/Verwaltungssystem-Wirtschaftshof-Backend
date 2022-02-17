package com.example.backend_verwaltungssoftware.services;

import com.example.backend_verwaltungssoftware.data.dtos.HolidayDto;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.Holiday;
import com.example.backend_verwaltungssoftware.data.internalRepresentation.User;
import com.example.backend_verwaltungssoftware.data.resources.HolidayResource;
import com.example.backend_verwaltungssoftware.manager.HolidayManager;
import com.example.backend_verwaltungssoftware.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HolidayService {
    @Autowired
    HolidayManager holidayManager;
    @Autowired
    UserManager userManager;

    public List<HolidayResource> getAll(){
        List<HolidayResource> result = new ArrayList<>();
        for (Holiday holiday :
                holidayManager.getAll()) {
            result.add(convertHolidayToHolidayResource(holiday));
        }
        return result;
    }

    public HolidayResource addHoliday(HolidayDto holidayDto){
        Holiday newHoliday = convertHolidayDtoToHoliday(holidayDto);
        return convertHolidayToHolidayResource(holidayManager.addHoliday(newHoliday));
    }

    private HolidayResource convertHolidayToHolidayResource(Holiday holiday){
        HolidayResource result = new HolidayResource();
        result.setId(holiday.getId());
        result.setBegin(holiday.getBegin());
        result.setEnd(holiday.getEnd());
        result.setReason(holiday.getReason());
        return result;
    }

    private Holiday convertHolidayDtoToHoliday(HolidayDto holidayDto){
        Holiday result = new Holiday();
        result.setId(-1);
        result.setBegin(holidayDto.getBegin());
        result.setEnd(holidayDto.getEnd());
        result.setReason(holidayDto.getReason());
        return result;
    }
}
