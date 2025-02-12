package com.msc.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msc.parking.dto.SlotsResponseDto;
import com.msc.parking.exception.CommonException;
import com.msc.parking.service.ShowSlotsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador responsável por gerenciar as requisições relacionadas à exibição
 * de vagas disponíveis.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Show Available Slots", description = "API for showing available parking slots")
public class ShowAvailableSlotsController {
    @Autowired
    ShowSlotsService showSlotsService;

    /**
     * Endpoint para exibir vagas de estacionamento disponíveis.
     * 
     * @return A resposta contendo a lista de vagas disponíveis.
     */
    @GetMapping("/showAvailableSlots")
    @Operation(summary = "Show available parking slots", description = "Endpoint to show available parking slots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vagas disponíveis listadas com sucesso", content = @Content(schema = @Schema(implementation = SlotsResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma vaga disponível encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    public ResponseEntity<List<SlotsResponseDto>> getAvailableSlots() {
        try {
            return new ResponseEntity<>(showSlotsService.getAllAvailableSlots(), HttpStatus.OK);
        } catch (CommonException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro interno do servidor");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Schema(description = "Response DTO for errors")
    public static class ErrorResponse {
        @Schema(description = "Error message", example = "Erro interno do servidor")
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