package com.msc.parking.dto;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for requesting a parking slot")
public class RequestSlotDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Registration ID", example = "12345", required = true)
    private Integer registrationId;

    @Schema(description = "Request date for the slot", example = "2025-02-01", required = true)
    private LocalDate requestDate;
}