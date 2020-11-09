package com.everis.parking.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.everis.parking.dto.RequestSlotDto;
import com.everis.parking.dto.RequestSlotResponseDto;
import com.everis.parking.service.RequestSlotService;

@RestController
public class RequestSlotController {
	private static final Logger logger = LoggerFactory.getLogger(RequestSlotController.class);
	@Autowired
	RequestSlotService requestSlotService;

	@PostMapping("/requestSlot")
	public ResponseEntity<RequestSlotResponseDto> requestSlot(@RequestBody RequestSlotDto requestSlotDto) {
		logger.info("Request slots {} : ", requestSlotDto.getRegistrationId());
		RequestSlotResponseDto response = requestSlotService.requestSlot(requestSlotDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
