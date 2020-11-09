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

import com.everis.parking.dto.LoginDto;
import com.everis.parking.dto.LoginResponseDto;
import com.everis.parking.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired 
	LoginService loginService;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
		logger.info("dentro do método de login..");
		return new ResponseEntity<>(loginService.loginUser(loginDto), HttpStatus.OK);
	}
}
