package com.everis.parking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.parking.dto.SlotsResponseDto;
import com.everis.parking.entity.AvailableSlot;
import com.everis.parking.exception.CommonException;
import com.everis.parking.repository.AvailableSlotRepository;
import com.everis.parking.util.ParkingConstants;

@Service
public class ShowSlotsServiceImpl implements ShowSlotsService {

	@Autowired
	AvailableSlotRepository availableSlotRepository;
	private static final Logger logger = LoggerFactory.getLogger(ShowSlotsServiceImpl.class);
	
	@Override
	public List<SlotsResponseDto> getAllAvailableSlots() {
		logger.info("Dentro do metodo getAllAvailableSlots ..");
		List<SlotsResponseDto> listslot = new ArrayList<>();
		LocalDate availableDate = LocalDate.now();
		List<AvailableSlot> listAvailable = availableSlotRepository.findAll();
		if(listAvailable.isEmpty())
			throw new CommonException(ParkingConstants.SLOTS_NOT_FOUND);
		listAvailable.stream().forEach(l->
		{
			if(l.getAvailableDate().equals(availableDate))
			{
				SlotsResponseDto slotsResponseDto = new SlotsResponseDto();
				BeanUtils.copyProperties(l, slotsResponseDto);
				listslot.add(slotsResponseDto);
			}
		});
		
		return listslot;
	}
}
