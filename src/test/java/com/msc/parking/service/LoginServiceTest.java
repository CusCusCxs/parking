package com.msc.parking.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.msc.parking.dto.LoginDto;
import com.msc.parking.dto.LoginResponseDto;
import com.msc.parking.entity.Registration;
import com.msc.parking.entity.Role;
import com.msc.parking.repository.RoleRepository;
import com.msc.parking.repository.UserRepository;
import com.msc.parking.service.LoginServiceImpl;
import com.msc.parking.util.PasswordUtil;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	RoleRepository roleRepository;
	@InjectMocks
	LoginServiceImpl loginService;

	Registration registration = getRegistration();
	LoginDto loginDto = getLoginDto();
	LoginResponseDto loginResponseDto = getLoginResponseDto();
	Role role = getRoleDetails();
	PasswordUtil pass = new PasswordUtil();

	public Registration getRegistration() {
		Registration registration = new Registration();
		registration.setMobileNumber("97392313");
		registration.setOverAllExperience(34.5F);
		registration.setMscExperience(5F);
		registration.setPassword("password");
		registration.setRegistrationId(1);
		registration.setRoleId(2);
		registration.setUserName("teutio");
		return registration;
	}

	public LoginResponseDto getLoginResponseDto() {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setRegId(1);
		loginResponseDto.setRoleName("V");
		loginResponseDto.setMessage("Login success");
		return loginResponseDto;
	}

	public LoginDto getLoginDto() {
		LoginDto loginDto = new LoginDto();
		loginDto.setMobileNo("97392313");
		
		String password = "1234";
	pass.encodePassword(password);
		loginDto.setPassword(pass.encodePassword(password));
		return loginDto;
	}

	public Role getRoleDetails() {
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("E");
		return role;
	}

	/**
	 * Este método tem como objetivo testar o método LoginUser.
	 */
	@Test
	public void testLoginUser() {
		Mockito.when(userRepository.findByMobileNumber(Mockito.anyString())).thenReturn(Optional.of(registration));
		Mockito.when(roleRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(role));
		LoginResponseDto response = loginService.loginUser(loginDto);
		Assert.assertEquals(loginResponseDto.getRegId(), response.getRegId());
	}
}
