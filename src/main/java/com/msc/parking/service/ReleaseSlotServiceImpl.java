package com.msc.parking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msc.parking.dto.ReleaseRequestDto;
import com.msc.parking.dto.ReleaseResponseDto;
import com.msc.parking.entity.AvailableSlot;
import com.msc.parking.entity.Registration;
import com.msc.parking.entity.Slot;
import com.msc.parking.exception.CommonException;
import com.msc.parking.repository.AvailableSlotRepository;
import com.msc.parking.repository.RegistrationRepository;
import com.msc.parking.repository.SlotRepository;
import com.msc.parking.util.ParkingConstants;

@Service
public class ReleaseSlotServiceImpl implements ReleaseSlotService {
	private static final Logger logger = LoggerFactory.getLogger(ReleaseSlotServiceImpl.class);

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	AvailableSlotRepository availableSlotRepository;

	@Override
	public ReleaseResponseDto releaseSlots(ReleaseRequestDto releaseRequestDto) {
		logger.info("Liberando slots em serviço {} : ", releaseRequestDto.getRegistrationId());
		Optional<Registration> registration = registrationRepository.findById(releaseRequestDto.getRegistrationId());
		if (!registration.isPresent())
			throw new CommonException(ParkingConstants.USER_NOT_FOUND + releaseRequestDto.getRegistrationId());
		Optional<Slot> slot = slotRepository.findByRegistrationId(releaseRequestDto.getRegistrationId());
		if (!slot.isPresent())
			throw new CommonException(ParkingConstants.SLOTS_NOT_FOUND + releaseRequestDto.getRegistrationId());
		LocalDate fromDate = releaseRequestDto.getFromDate();
		LocalDate toDate = releaseRequestDto.getToDate();
		if (fromDate.compareTo(LocalDate.now()) != 0 && fromDate.compareTo(LocalDate.now()) < 0)
			throw new CommonException(ParkingConstants.ERROR_FROM_DATE);
		if (fromDate.compareTo(toDate) != 0 && fromDate.compareTo(toDate) > 0)
			throw new CommonException(ParkingConstants.ERROR_TO_DATE);
		List<LocalDate> dates = new ArrayList<>();
		LocalDate currentDate = fromDate;
		ReleaseResponseDto releaseResponseDto = new ReleaseResponseDto();
		while (currentDate.isBefore(toDate) || currentDate.equals(toDate)) {
			dates.add(currentDate);
			AvailableSlot availableSlot = new AvailableSlot();
			availableSlot.setSlotId(slot.get().getSlotId());
			availableSlot.setStatus("acessível");
			availableSlot.setVipId(releaseRequestDto.getRegistrationId());
			availableSlot.setAvailableDate(currentDate);
			AvailableSlot response = availableSlotRepository.save(availableSlot);
			logger.info("ID de slot disponível {} : ", response.getAvailableSlotId());
			currentDate = currentDate.plusDays(1);
			releaseResponseDto.setMessage("Slot lançado com sucesso");
		}
		return releaseResponseDto;
	}
}
