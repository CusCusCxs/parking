package com.everis.parking.service;

import org.springframework.stereotype.Service;

import com.everis.parking.dto.LoginDto;
import com.everis.parking.dto.LoginResponseDto;

@Service
public interface LoginService {
	
	public LoginResponseDto loginUser(LoginDto loginDto);
}
