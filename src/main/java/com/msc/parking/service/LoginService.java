
package com.msc.parking.service;

import org.springframework.stereotype.Service;

import com.msc.parking.dto.LoginDto;
import com.msc.parking.dto.LoginResponseDto;

@Service
public interface LoginService {
	
	public LoginResponseDto loginUser(LoginDto loginDto);
}
