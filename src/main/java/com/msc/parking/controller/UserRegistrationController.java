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

import com.msc.parking.dto.UserRegistrationRequestDto;
import com.msc.parking.dto.UserRegistrationResponseDto;
import com.msc.parking.exception.CommonException;
import com.msc.parking.service.UserRegistrationServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Esta classe representa o controlador para registro de usuários. Ele lida com
 * as solicitações HTTP relacionadas ao registro de usuários.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "User Registration", description = "API for user registration")
public class UserRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	UserRegistrationServiceImpl userRegistrationServiceImpl;

	/**
	 * Endpoint para registro de usuários.
	 * 
	 * @param userRegistrationRequestDto O DTO contendo os detalhes do registro.
	 * @return A entidade de resposta com os detalhes do registro.
	 */
	@PostMapping("/register")
	@Operation(summary = "Register a user", description = "Endpoint to register a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Registro realizado com sucesso", content = @Content(schema = @Schema(implementation = UserRegistrationResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public ResponseEntity<UserRegistrationResponseDto> registerUser(
			@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
		LOGGER.info("no registro do controller do usuário");
		UserRegistrationResponseDto response = userRegistrationServiceImpl.registerUser(userRegistrationRequestDto);
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