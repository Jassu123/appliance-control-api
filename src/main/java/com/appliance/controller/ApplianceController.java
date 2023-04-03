package com.appliance.controller;
import com.appliance.model.ApplianceDto;
import com.appliance.service.ApplianceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@Tag(
        name = "CRUD REST APIs for Appliance Resource",
        description = "CRUD REST APIs - Appliance control api with all crud operations"
)

@RestController
@RequestMapping("/appliance/state")
public class ApplianceController {
    @Autowired
    private ApplianceService applianceService;

    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }
    @Operation(
            summary = "Create Appliance REST API",
            description = "Create Appliance REST API is used to save appliance information in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<ApplianceDto> saveStateAppliance( @RequestBody ApplianceDto applianceDto){
        ApplianceDto savedResponse= applianceService.saveAppliance(applianceDto);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get applaince By ID REST API",
            description = "Get applaiance By ID REST API is used to get a single applaince state from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<Optional<ApplianceDto>> getApplianceById(@PathVariable("id") Long Id){
        Optional<ApplianceDto> applianceDto = Optional.ofNullable(applianceService.getAppliance(Id));
        return new ResponseEntity<>(applianceDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update appliance state REST API",
            description = "Update appliance REST API is used to update a particular appliance in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<ApplianceDto> updateAppliance(@PathVariable("id") Long Id,
                                              @RequestBody  ApplianceDto applianceDto){
        applianceDto.setId(Id);
        ApplianceDto updatedAppliance = applianceService.updateAppliance(applianceDto);
        return new ResponseEntity<>(updatedAppliance, HttpStatus.OK);
    }
    @Operation(
            summary = "Delete appliance REST API",
            description = "Delete applaince REST API is used to delete a particular reset state from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public void resetAppliance(@PathVariable Long id) {
        applianceService.resetAppliance(id);
    }
}
