package com.appliance.controller;

import com.appliance.model.ApplianceDto;
import com.appliance.service.ApplianceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class ApplainceControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplianceService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenApplainceObject_whenCreateApplaince_thenReturnSavedApplaince() throws Exception{
        ApplianceDto applianceDto = ApplianceDto.builder()
                .state("ON")
                .build();
        given(service.saveAppliance(any(ApplianceDto.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(post("/appliance/state")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applianceDto)));
        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.state",
                        is(applianceDto.getState())));
    }

    // JUnit test for delete user REST API
    @Test
    public void givenApplainceId_whenDeleteApplaince_thenReturn200() throws Exception{
        // given - precondition or setup
        long id = 1L;
        willDoNothing().given(service).resetAppliance(id);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/appliance/state/{id}", id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
