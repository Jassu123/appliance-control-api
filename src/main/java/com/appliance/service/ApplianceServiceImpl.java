package com.appliance.service;
import com.appliance.entity.ApplianceEntity;
import com.appliance.exception.ApplianceNotFoundException;
import com.appliance.mapper.ApplianceMapper;
import com.appliance.model.ApplianceDto;
import com.appliance.repository.ApplianceRepository;
import com.appliance.utils.RestApiConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplianceServiceImpl implements ApplianceService{
    private ApplianceRepository repository;

    @Override
    public ApplianceDto saveAppliance(ApplianceDto applianceDto) {

        ApplianceEntity applianceEntity = ApplianceMapper.mapToApplianceEntity(applianceDto);
        ApplianceEntity savedAppliance = repository.save(applianceEntity);
        return ApplianceMapper.mapToApplianceDto(savedAppliance);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ApplianceDto getAppliance(Long id) {
        ApplianceEntity entity=repository.findById(id)
                .orElseThrow(() ->  new ApplianceNotFoundException("Appliance", "id", id));
        return ApplianceMapper.mapToApplianceDto(entity);
    }

    /**
     * @param applianceDto
     * @return
     */
    @Override
    public ApplianceDto updateAppliance(ApplianceDto applianceDto) {
        ApplianceEntity existingAppliance = repository.findById(applianceDto.getId())
                .orElseThrow(() -> new ApplianceNotFoundException("Appliance", "id", applianceDto.getId()));
        existingAppliance.setState(applianceDto.getState());
         ApplianceEntity updatedState= repository.save(existingAppliance);
        ApplianceDto applianceDto1 = ApplianceMapper.mapToApplianceDto(updatedState);
        return applianceDto1;
    }

    /**
     * @param id
     */
    @Override
    public void resetAppliance(Long id) {
        ApplianceEntity existingAppliance = repository.findById(id)
                .orElseThrow(() -> new ApplianceNotFoundException("Appliance", "id", id));
        existingAppliance.setState(RestApiConstants.DEFAULT_STATE);
        repository.save(existingAppliance);
    }
}
