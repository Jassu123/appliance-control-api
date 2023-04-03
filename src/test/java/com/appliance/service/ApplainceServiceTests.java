package com.appliance.service;
import com.appliance.entity.ApplianceEntity;
import com.appliance.model.ApplianceDto;
import com.appliance.repository.ApplianceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
public class ApplainceServiceTests {
    @Mock
    private ApplianceRepository repository;

    @InjectMocks
    private ApplianceServiceImpl applianceServiceImpl;

    private ApplianceEntity entity;
    private ApplianceEntity entity2;

    private ApplianceDto entityDto;
    private List<ApplianceEntity> entityList;
    @BeforeEach
    public void setup(){
       entity = ApplianceEntity.builder()
                .state("ON")
                .build();
      entity2 = ApplianceEntity.builder()
                .state("ON")
                .build();
        entityList = List.of(entity, entity2);
        entityDto = ApplianceDto.builder()
                .state("ON")
                .build();
    }
    @Test
    @DisplayName("JUnit test for update Applaince method")
    public void givenApplaince_whenUpdateApplaince_thenReturnUpdatedApplaince() {
        // given - precondition or setup
        given(repository.findById(entity.getId())).willReturn(Optional.of(entity));
        given(repository.save(entity)).willReturn(entity);
        // when - action or the behavior that we are going to test
        ApplianceDto updatedApplaince = applianceServiceImpl.updateAppliance(entityDto);
        // then - verify the output
        assertThat(updatedApplaince).isNotNull();
        assertThat(updatedApplaince.getState()).isEqualTo(entityDto.getState());
    }
}
