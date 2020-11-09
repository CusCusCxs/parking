package com.everis.parking.service;

import com.everis.parking.dto.ReleaseRequestDto;
import com.everis.parking.dto.ReleaseResponseDto;

public interface ReleaseSlotService {

	ReleaseResponseDto releaseSlots(ReleaseRequestDto releaseRequestDto);

}
