package com.msc.parking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.msc.parking.dto.RequestSlotDto;
import com.msc.parking.dto.RequestSlotResponseDto;
import com.msc.parking.exception.CommonException;
import com.msc.parking.service.RequestSlotService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador responsável por gerenciar as requisições relacionadas à solicitação de vagas.
 */
@RestController
@Tag(name = "Request Slot", description = "API for requesting parking slots")
public class RequestSlotController {
    private static final Logger logger = LoggerFactory.getLogger(RequestSlotController.class);

    @Autowired
    RequestSlotService requestSlotService;

    /**
     * Endpoint para solicitar vagas de estacionamento.
     * 
     * @param requestSlotDto O objeto contendo os dados da solicitação de vaga.
     * @return A resposta contendo os detalhes da solicitação de vaga.
     */
    @PostMapping("/requestSlot")
    @Operation(summary = "Request a parking slot", description = "Endpoint to request a parking slot")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitação de slot realizada com sucesso", content = @Content(schema = @Schema(implementation = RequestSlotResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<RequestSlotResponseDto> requestSlot(@RequestBody RequestSlotDto requestSlotDto) {
        logger.info("Request slots {} : ", requestSlotDto.getRegistrationId());
        RequestSlotResponseDto response = requestSlotService.requestSlot(requestSlotDto);
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