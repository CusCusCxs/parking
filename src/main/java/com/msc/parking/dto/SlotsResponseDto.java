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
@Schema(description = "Response DTO for available parking slots")
public class SlotsResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Available slot ID", example = "1", required = true)
    private Integer availableSlotId;

    @Schema(description = "Available date", example = "2025-02-01", required = true)
    private LocalDate availableDate;

    @Schema(description = "Slot ID", example = "A1", required = true)
    private Integer slotId;

    @Schema(description = "Status of the slot", example = "available", required = true)
    private String status;

    @Schema(description = "VIP ID", example = "12345")
    private Integer vipId;

    @Schema(description = "Booked employee ID", example = "67890")
    private Integer bookedEmpId;
}