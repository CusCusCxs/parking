package com.everis.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.parking.dto.SlotsResponseDto;
import com.everis.parking.service.ShowSlotsService;

@RestController
@RequestMapping("/api")
public class ShowAvailableSlotsController {
	@Autowired
	ShowSlotsService showSlotsService;

	@GetMapping("/showAvailableSlots")
	public ResponseEntity<List<SlotsResponseDto>> getAvailableSlots() {
		return new ResponseEntity<>(showSlotsService.getAllAvailableSlots(), HttpStatus.OK);
	}
}
