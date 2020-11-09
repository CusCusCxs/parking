package com.everis.parking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.parking.dto.RequestSlotDto;
import com.everis.parking.dto.RequestSlotResponseDto;
import com.everis.parking.entity.RequestSlot;
import com.everis.parking.exception.CommonException;
import com.everis.parking.repository.RequestSlotRepository;
import com.everis.parking.util.ParkingConstants;

@Service
public class RequestSlotServiceImpl implements RequestSlotService {
	private static final Logger logger = LoggerFactory.getLogger(RequestSlotServiceImpl.class);
	@Autowired
	RequestSlotRepository requestSlotRepository;

	@Override
	public RequestSlotResponseDto requestSlot(RequestSlotDto requestSlotDto) {
		logger.info("Solicitar serviço de slot {} : ", requestSlotDto.getRegistrationId());
		RequestSlot requestSlot = new RequestSlot();
		RequestSlotResponseDto response = new RequestSlotResponseDto();
		if (requestSlotDto.getRegistrationId() == null)
			throw new CommonException(ParkingConstants.USER_NOT_FOUND);
		else {
			requestSlot.setRegistrationId(requestSlotDto.getRegistrationId());
			requestSlot.setRequestDate(requestSlotDto.getRequestDate());
			requestSlotRepository.save(requestSlot);
			response.setMessage("Pedido enviado com sucesso");
		}
		return response;

	}

}
