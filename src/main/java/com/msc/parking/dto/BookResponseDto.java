package com.msc.parking.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for booking a parking slot")
public class BookResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Response message", example = "Booking successful", required = true)
	private String message;

	@JsonInclude(Include.NON_NULL)
	@Schema(description = "Slot ID", example = "A1")
	private String slotId;
}