package com.msc.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msc.parking.dto.LoginDto;
import com.msc.parking.dto.LoginResponseDto;
import com.msc.parking.exception.UserNotFoundException;
import com.msc.parking.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Esta classe representa o controlador para login de usuários. Ele lida com as
 * solicitações HTTP relacionadas ao login.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Login", description = "API for user login")
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * Endpoint para login de usuários.
	 * 
	 * @param loginDto O DTO contendo os detalhes do login.
	 * @return A entidade de resposta com os detalhes do login.
	 */
	@PostMapping("/login")
	@Operation(summary = "User login", description = "Endpoint to login a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Credenciais inválidas", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDto) {
		return new ResponseEntity<>(loginService.loginUser(loginDto), HttpStatus.OK);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ApiResponse(responseCode = "400", description = "Credenciais inválidas", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
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
		@Schema(description = "Error message", example = "Credenciais inválidas")
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