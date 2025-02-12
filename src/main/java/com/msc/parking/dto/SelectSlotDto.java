package com.msc.parking.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for selecting a parking slot")
public class SelectSlotDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Available slot ID", example = "1", required = true)
    private Integer availableSlotId;
}