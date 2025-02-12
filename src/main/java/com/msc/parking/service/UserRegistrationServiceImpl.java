package com.msc.parking.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msc.parking.dto.UserRegistrationRequestDto;
import com.msc.parking.dto.UserRegistrationResponseDto;
import com.msc.parking.entity.Registration;
import com.msc.parking.entity.Slot;
import com.msc.parking.exception.CommonException;
import com.msc.parking.repository.RegistrationRepository;
import com.msc.parking.repository.SlotRepository;
import com.msc.parking.util.ParkingConstants;
import com.msc.parking.util.PasswordUtil;

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

		// Verificar se o número de celular já está registrado
		Optional<Registration> registration = registrationRepository
				.findByMobileNumber(userRegistrationRequestDto.getMobileNumber());
		if (registration.isPresent()) {
			throw new CommonException(ParkingConstants.USER_ALREADY_REGISTERED);
		}

		// Validar número de celular
		if (!validMobileNumber(userRegistrationRequestDto.getMobileNumber())) {
			throw new CommonException(ParkingConstants.INVALID_MOBILENUMBER);
		}

		// Validar nome do usuário
		if (!validateCustomerName(userRegistrationRequestDto.getUserName())) {
			throw new CommonException(ParkingConstants.INVALID_NAME);
		}

		// Definir os dados do registro
		register.setMobileNumber(userRegistrationRequestDto.getMobileNumber());
		register.setMscExperience(userRegistrationRequestDto.getMscExperience());
		register.setOverAllExperience(userRegistrationRequestDto.getOverAllExperience());
		register.setPassword(passwordUtil.encodePassword(userRegistrationRequestDto.getPassword()));
		register.setUserName(userRegistrationRequestDto.getUserName());

		// Definir o papel do usuário com base na experiência
		if (userRegistrationRequestDto.getOverAllExperience() >= 15
				&& userRegistrationRequestDto.getMscExperience() >= 5) {
			register.setRoleId(2); // Papel específico para usuários com alta experiência
		} else {
			register.setRoleId(3); // Papel padrão para outros usuários
		}

		// Salvar o registro do usuário
		Registration reg = registrationRepository.save(register);

		// Criar um slot se o usuário tiver alta experiência
		if (userRegistrationRequestDto.getOverAllExperience() >= 15
				&& userRegistrationRequestDto.getMscExperience() >= 5) {
			Slot slot = new Slot();
			slot.setSlotName("A" + reg.getRegistrationId());
			slot.setRegistrationId(reg.getRegistrationId());
			slotRepository.save(slot);
		}

		response.setMessage("Usuário registrado com sucesso");
		response.setRegistrationId(reg.getRegistrationId());
		return response;
	}

	private boolean validMobileNumber(String number) {
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Matcher m = p.matcher(number);
		return (m.find() && m.group().equals(number));
	}

	private boolean validateCustomerName(String customerName) {
		String name = "^[a-zA-Z]+$";
		return customerName.matches(name);
	}
}