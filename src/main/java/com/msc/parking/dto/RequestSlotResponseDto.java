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
@Schema(description = "Response DTO for requesting a parking slot")
public class RequestSlotResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Response message", example = "Solicitação de slot realizada com sucesso", required = true)
    private String message;
}