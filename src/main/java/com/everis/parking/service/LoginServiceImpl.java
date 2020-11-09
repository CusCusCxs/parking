package com.everis.parking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.parking.dto.LoginDto;
import com.everis.parking.dto.LoginResponseDto;
import com.everis.parking.entity.Registration;
import com.everis.parking.entity.Role;
import com.everis.parking.exception.UserNotFoundException;
import com.everis.parking.repository.RoleRepository;
import com.everis.parking.repository.UserRepository;
import com.everis.parking.util.PasswordUtil;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


	
	@Override
	public LoginResponseDto loginUser(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		PasswordUtil passwordUtil = new PasswordUtil();
		logger.info("inside the loginUser method..");
		Optional<Registration> registration = userRepository.findByMobileNumber(loginDto.getMobileNo());
		if (!registration.isPresent())
			throw new UserNotFoundException("Invalid credentials");
		Optional<Role> role = roleRepository.findById(registration.get().getRoleId());
		if (!role.isPresent())
			throw new UserNotFoundException("Role id not available");
		if (registration.get().getMobileNumber().equalsIgnoreCase(loginDto.getMobileNo())
				&& registration.get().getPassword().equals(passwordUtil.encodePassword(loginDto.getPassword()))) {
			loginResponseDto.setMessage("Login success");
			loginResponseDto.setRegId(registration.get().getRegistrationId());
			loginResponseDto.setRoleName(role.get().getRoleName());
		}
		return loginResponseDto;
	}
}
