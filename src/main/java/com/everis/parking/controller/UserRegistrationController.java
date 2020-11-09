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

import com.everis.parking.dto.UserRegistrationRequestDto;
import com.everis.parking.dto.UserRegistrationResponseDto;
import com.everis.parking.service.UserRegistrationServiceImpl;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	UserRegistrationServiceImpl userRegistrationServiceImpl;

	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponseDto> registerUser(
			@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
		LOGGER.info("no registro do controller do usuário");
		UserRegistrationResponseDto response = userRegistrationServiceImpl.registerUser(userRegistrationRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
