package com.appliance.intgration;
import com.appliance.entity.ApplianceEntity;
import com.appliance.model.ApplianceDto;
import com.appliance.repository.ApplianceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplainceControllerITests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplianceRepository applianceRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setup(){
        applianceRepository.deleteAll();
    }
    @Test
    public void givenApplainceObject_whenCreateApplaince_thenReturnSavedApplaince() throws Exception{

        // given - precondition or setup
        ApplianceDto dto = ApplianceDto.builder()
                .state("ON")
                .build();

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/appliance/state")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.state",
                        is(dto.getState())));

    }
    // JUnit test for update Appliance REST API - positive scenario
    @Test
    public void givenUpdatedAppliance_whenUpdateAppliance_thenReturnUpdateApplianceObject() throws Exception{
        // given - precondition or setup
        ApplianceEntity entity = ApplianceEntity.builder()
                .state("ON")
                .build();
        applianceRepository.save(entity);
        ApplianceEntity entity1 = ApplianceEntity.builder()
                .state("OFF")
                .build();
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/appliance/state/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity1)));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.state", is(entity1.getState())));

    }
    // JUnit test for delete user REST API
    @Test
    public void givenApplianceId_whenDeleteAppliance_thenReturn200() throws Exception{
        // given - precondition or setup
        long id = 1L;
        // given - precondition or setup
        ApplianceEntity entity = ApplianceEntity.builder()
                .state("ON")
                .build();
        applianceRepository.save(entity);
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/appliance/state/{id}", entity.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
