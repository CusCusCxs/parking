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
@Schema(description = "Response DTO for releasing parking slots")
public class ReleaseResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "Response message", example = "Slot lançado com sucesso", required = true)
	private String message;
}
