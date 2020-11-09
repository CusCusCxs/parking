package com.everis.parking.service;

import com.everis.parking.dto.RequestSlotDto;
import com.everis.parking.dto.RequestSlotResponseDto;

public interface RequestSlotService {

	RequestSlotResponseDto requestSlot(RequestSlotDto requestSlotDto);

}
