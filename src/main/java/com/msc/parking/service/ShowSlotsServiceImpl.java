package com.msc.parking.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msc.parking.dto.SlotsResponseDto;
import com.msc.parking.entity.AvailableSlot;
import com.msc.parking.exception.CommonException;
import com.msc.parking.repository.AvailableSlotRepository;
import com.msc.parking.util.ParkingConstants;

@Service
public class ShowSlotsServiceImpl implements ShowSlotsService {

	@Autowired
	AvailableSlotRepository availableSlotRepository;
	private static final Logger logger = LoggerFactory.getLogger(ShowSlotsServiceImpl.class);
	
	@Override
	public List<SlotsResponseDto> getAllAvailableSlots() {
		logger.info("Dentro do metodo getAllAvailableSlots ..");
		List<SlotsResponseDto> listslot = new ArrayList<>();
		LocalDate availableDate = LocalDate.now(ZoneId.of("UTC"));
		logger.info("Data atual: {}", availableDate);
		List<AvailableSlot> listAvailable = availableSlotRepository.findByAvailableDate(availableDate);
		logger.info("Slots disponíveis encontrados: {}", listAvailable.size());
		if (listAvailable.isEmpty()) {
			logger.warn("Nenhum slot disponível encontrado para a data: {}", availableDate);
			throw new CommonException(ParkingConstants.SLOTS_NOT_FOUND);
		}
		listAvailable.forEach(l -> {
			SlotsResponseDto slotsResponseDto = new SlotsResponseDto();
			BeanUtils.copyProperties(l, slotsResponseDto);
			listslot.add(slotsResponseDto);
		});
	
		return listslot;
	}
}
