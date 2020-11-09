package com.everis.parking.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.everis.parking.dto.SlotsResponseDto;

@Service
public interface ShowSlotsService {
	public List<SlotsResponseDto> getAllAvailableSlots();
}
