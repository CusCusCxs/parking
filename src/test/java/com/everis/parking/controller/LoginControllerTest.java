package com.everis.parking.controller;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.everis.parking.controller.LoginController;
import com.everis.parking.dto.LoginDto;
import com.everis.parking.dto.LoginResponseDto;
import com.everis.parking.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {
	@Mock LoginService loginService;
	@InjectMocks LoginController loginController;
	private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);
	MockMvc mockMvc;
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}
	@Test
	public void userLoginTest() throws Exception{
	logger.info("dentro do método userLoginTest");
	LoginDto loginDto = new LoginDto();
	loginDto.setMobileNo("1234567891");
	loginDto.setPassword("12345");
	LoginResponseDto login = new LoginResponseDto();
	login.setRegId(1);
	login.setRoleName("A");
	mockMvc.perform(MockMvcRequestBuilders.post("/api/login").contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(loginDto))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
}