package com.msc.parking.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for requesting parking slots by day")
public class SlotRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Day for which slots are requested", example = "Monday", required = true)
    private String day;
}
