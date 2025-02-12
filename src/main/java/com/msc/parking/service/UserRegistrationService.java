package com.msc.parking.service;

import org.springframework.stereotype.Service;

import com.msc.parking.dto.UserRegistrationRequestDto;
import com.msc.parking.dto.UserRegistrationResponseDto;

@Service
public interface UserRegistrationService {

	public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
