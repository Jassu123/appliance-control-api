package com.appliance.mapper;

import com.appliance.entity.ApplianceEntity;
import com.appliance.model.ApplianceDto;

public class ApplianceMapper {
    public static ApplianceDto mapToApplianceDto(ApplianceEntity entity){
        return new ApplianceDto(
                entity.getId(),
                entity.getState()

        );
    }
    public static ApplianceEntity mapToApplianceEntity(ApplianceDto dto){
        return new ApplianceEntity(
                dto.getId(),
                dto.getState()

        );
    }
}
