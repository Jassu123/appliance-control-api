package com.appliance.service;
import com.appliance.model.ApplianceDto;

public interface ApplianceService {

    ApplianceDto saveAppliance(ApplianceDto applianceDto);
    ApplianceDto getAppliance(Long id);
    ApplianceDto updateAppliance(ApplianceDto applianceDto);

    void resetAppliance(Long id);

}
