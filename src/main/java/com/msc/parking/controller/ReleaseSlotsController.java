package com.msc.parking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msc.parking.dto.ReleaseRequestDto;
import com.msc.parking.dto.ReleaseResponseDto;
import com.msc.parking.exception.CommonException;
import com.msc.parking.service.ReleaseSlotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador responsável por gerenciar as requisições relacionadas à liberação
 * de vagas.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Release Slots", description = "API for releasing parking slots")
public class ReleaseSlotsController {
	private static final Logger logger = LoggerFactory.getLogger(ReleaseSlotsController.class);

	@Autowired
	ReleaseSlotService releaseSlotService;

	/**
	 * Endpoint para liberar vagas de estacionamento.
	 * 
	 * @param releaseRequestDto O objeto contendo os dados da solicitação de
	 *                          liberação de vagas.
	 * @return A resposta contendo os detalhes da liberação de vagas.
	 */
	@PostMapping("/release")
	@Operation(summary = "Release parking slots", description = "Endpoint to release parking slots")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Slot lançado com sucesso", content = @Content(schema = @Schema(implementation = ReleaseResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public ResponseEntity<ReleaseResponseDto> releaseSlots(@RequestBody ReleaseRequestDto releaseRequestDto) {
		logger.info("Release slots {} : ", releaseRequestDto.getRegistrationId());
		ReleaseResponseDto response = releaseSlotService.releaseSlots(releaseRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@ExceptionHandler(CommonException.class)
	@ApiResponse(responseCode = "400", description = "Erro de negócio", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse("Erro interno do servidor");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Schema(description = "Response DTO for errors")
	public static class ErrorResponse {
		@Schema(description = "Error message", example = "Erro de negócio")
		private String message;

		public ErrorResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}