package com.appliance.model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Appliance Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplianceDto  {
    private Long id;
    private String state;
}
