package com.appliance.repository;
import com.appliance.entity.ApplianceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ApplianceRepositoryTests {
    @Autowired
    private ApplianceRepository applianceRepository;
    private ApplianceEntity applianceEntity;
    @BeforeEach
    public void setup(){

        ApplianceEntity entity = ApplianceEntity.builder()
                .state("ON")
                .build();
    }
    @DisplayName("JUnit test for save appliance operation")
    @Test
    public void givenApplianceObject_whenSave_thenReturnSavedAppliance(){

        //given - precondition or setup
        ApplianceEntity entity = ApplianceEntity.builder()
                .state("ON")
                .build();
        // when - action or the behaviour that we are going test
        ApplianceEntity savedApplaince = applianceRepository.save(entity);

        // then - verify the output
        assertThat(savedApplaince).isNotNull();
        assertThat(savedApplaince.getId()).isGreaterThan(0);
    }
    // JUnit test for get all appliance operation
    @DisplayName("JUnit test for get all appliance operation")
    @Test
    public void givenApplianceList_whenFindAll_thenAppliancesList(){

        //given - precondition or setup
        ApplianceEntity entity = ApplianceEntity.builder()
                .state("ON")
                .build();

        applianceRepository.save(entity);

        // when -  action or the behaviour that we are going test
        List<ApplianceEntity> applianceEntityList = applianceRepository.findAll();

        // then - verify the output
        assertThat(applianceEntityList).isNotNull();
        assertThat(applianceEntityList.size()).isEqualTo(1);
    }
    // JUnit test for delete appliance operation
    @DisplayName("JUnit test for delete appliance operation")
    @Test
    public void givenUserObject_whenDelete_thenRemoveUser(){
        //given - precondition or setup
        ApplianceEntity entity = ApplianceEntity.builder()
                .state("ON")
                .build();
        applianceRepository.save(entity);
        // when -  action or the behaviour that we are going test
        applianceRepository.deleteById(entity.getId());
        Optional<ApplianceEntity> entity1 = applianceRepository.findById(entity.getId());
        // then - verify the output
        assertThat(entity1).isEmpty();
    }
}
