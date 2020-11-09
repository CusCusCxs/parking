package com.everis.parking.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.parking.dto.UserRegistrationRequestDto;
import com.everis.parking.dto.UserRegistrationResponseDto;
import com.everis.parking.entity.Registration;
import com.everis.parking.entity.Slot;
import com.everis.parking.exception.CommonException;
import com.everis.parking.repository.RegistrationRepository;
import com.everis.parking.repository.SlotRepository;
import com.everis.parking.util.ParkingConstants;
import com.everis.parking.util.PasswordUtil;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	private static Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	SlotRepository slotRepository;

	@Override
	public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {
		logger.info("no serviço de registro do usuário");
		UserRegistrationResponseDto response = new UserRegistrationResponseDto();
		Registration register = new Registration();
		PasswordUtil passwordUtil = new PasswordUtil();
		Optional<Registration> registration = registrationRepository
				.findByMobileNumber(userRegistrationRequestDto.getMobileNumber());
		if (registration.isPresent())
			throw new CommonException(ParkingConstants.USER_ALREADY_REGISTERED);
		if (!validMobileNumber(userRegistrationRequestDto.getMobileNumber())) {
			throw new CommonException(ParkingConstants.INVALID_MOBILENUMBER);
		}

		if (!validateCustomerName(userRegistrationRequestDto.getUserName())) {
			throw new CommonException(ParkingConstants.INVALID_NAME);
		}

		if (userRegistrationRequestDto.getOverAllExperience() >= 15
				&& userRegistrationRequestDto.getEverisExperience() >= 5) {
			register.setMobileNumber(userRegistrationRequestDto.getMobileNumber());
			register.setEverisExperience(userRegistrationRequestDto.getEverisExperience());
			register.setOverAllExperience(userRegistrationRequestDto.getOverAllExperience());
			register.setPassword(passwordUtil.encodePassword(userRegistrationRequestDto.getPassword()));
			register.setRoleId(2);
			register.setUserName(userRegistrationRequestDto.getUserName());

		} else {
			register.setMobileNumber(userRegistrationRequestDto.getMobileNumber());
			register.setEverisExperience(userRegistrationRequestDto.getEverisExperience());
			register.setOverAllExperience(userRegistrationRequestDto.getOverAllExperience());
			register.setPassword(passwordUtil.encodePassword(userRegistrationRequestDto.getPassword()));
			register.setRoleId(3);
			register.setUserName(userRegistrationRequestDto.getUserName());
		}

		Registration reg = registrationRepository.save(register);

		if (userRegistrationRequestDto.getOverAllExperience() >= 15
				&& userRegistrationRequestDto.getEverisExperience() >= 5) {
			Slot slot = new Slot();
			slot.setSlotName("A" + reg.getRegistrationId());
			slot.setRegistrationId(reg.getRegistrationId());
			slotRepository.save(slot);
		}

		response.setMessage("Usuário registrado com sucesso");
		return response;
	}

	private boolean validMobileNumber(String number) {
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Matcher m = p.matcher(number);
		return (m.find() && m.group().equals(number));
	}

	private boolean validateCustomerName(String customerName) {
		String name = ("^[a-zA-Z]*$");
		return customerName.matches(name);
	}
}
