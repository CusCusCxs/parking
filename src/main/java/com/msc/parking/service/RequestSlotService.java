package com.msc.parking.service;

import com.msc.parking.dto.RequestSlotDto;
import com.msc.parking.dto.RequestSlotResponseDto;

public interface RequestSlotService {

	RequestSlotResponseDto requestSlot(RequestSlotDto requestSlotDto);

}
