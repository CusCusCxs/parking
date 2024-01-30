package com.everis.parking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.parking.dto.ReleaseRequestDto;
import com.everis.parking.dto.ReleaseResponseDto;
import com.everis.parking.service.ReleaseSlotService;

/**
 * Controlador responsável por gerenciar as requisições relacionadas à liberação de vagas.
 */
@RestController
@RequestMapping("/api")
public class ReleaseSlotsController {
	private static final Logger logger = LoggerFactory.getLogger(ReleaseSlotsController.class);
	@Autowired
	ReleaseSlotService releaseSlotService;

	/**
	 * Endpoint para liberar vagas de estacionamento.
	 * 
	 * @param releaseRequestDto O objeto contendo os dados da solicitação de liberação de vagas.
	 * @return A resposta contendo os detalhes da liberação de vagas.
	 */
	@PostMapping("/release")
	public ResponseEntity<ReleaseResponseDto> releaseSlots(@RequestBody ReleaseRequestDto releaseRequestDto) {
		logger.info("Release slots {} : ", releaseRequestDto.getRegistrationId());
		ReleaseResponseDto response = releaseSlotService.releaseSlots(releaseRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
