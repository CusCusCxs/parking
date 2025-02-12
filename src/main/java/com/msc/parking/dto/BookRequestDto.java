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
@Schema(description = "Request DTO for booking a parking slot")
public class BookRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Registration ID", example = "12345", required = true)
	private Integer regId;

	@Schema(description = "Available Slot ID", example = "A1", required = true)
	private Integer availableSlotId;
}