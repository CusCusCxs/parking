package com.msc.parking.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msc.parking.dto.SlotsResponseDto;

@Service
public interface ShowSlotsService {
	public List<SlotsResponseDto> getAllAvailableSlots();
}
