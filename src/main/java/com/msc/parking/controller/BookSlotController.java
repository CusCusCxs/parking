package com.msc.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msc.parking.dto.BookRequestDto;
import com.msc.parking.dto.BookResponseDto;
import com.msc.parking.exception.CommonException;
import com.msc.parking.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Esta classe representa o controlador para reserva de vagas de estacionamento.
 * Ele lida com as solicitações HTTP relacionadas à reserva de vagas e
 * realização de sorteios.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Book Slot", description = "API for booking parking slots")
public class BookSlotController {

	@Autowired
	BookService bookService;

	/**
	 * Endpoint para reserva de vagas de estacionamento.
	 * 
	 * @param bookRequestDto O DTO contendo os detalhes da reserva.
	 * @return A entidade de resposta com os detalhes da vaga reservada.
	 */
	@PostMapping("/bookSlot")
	@Operation(summary = "Book a parking slot", description = "Endpoint to book a parking slot")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Slot reservado com sucesso", content = @Content(schema = @Schema(implementation = BookResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public ResponseEntity<BookResponseDto> bookSlots(@RequestBody BookRequestDto bookRequestDto) {
		return new ResponseEntity<>(bookService.bookSlot(bookRequestDto), HttpStatus.CREATED);
	}

	/**
	 * Endpoint for performing a raffle to select a winner.
	 * 
	 * @return The response entity with the winner's details.
	 */
	@PostMapping("/doRaffle")
	@Operation(summary = "Perform a raffle", description = "Endpoint to perform a raffle and select a winner")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Raffle realizado com sucesso", content = @Content(schema = @Schema(implementation = BookResponseDto.class))),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public ResponseEntity<BookResponseDto> doRaffle() {
		return new ResponseEntity<>(bookService.doRaffle(), HttpStatus.CREATED);
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