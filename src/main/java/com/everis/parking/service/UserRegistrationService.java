package com.everis.parking.service;

import org.springframework.stereotype.Service;

import com.everis.parking.dto.UserRegistrationRequestDto;
import com.everis.parking.dto.UserRegistrationResponseDto;

@Service
public interface UserRegistrationService {

	public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
