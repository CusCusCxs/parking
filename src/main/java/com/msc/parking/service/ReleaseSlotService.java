package com.msc.parking.service;

import com.msc.parking.dto.ReleaseRequestDto;
import com.msc.parking.dto.ReleaseResponseDto;

public interface ReleaseSlotService {

	ReleaseResponseDto releaseSlots(ReleaseRequestDto releaseRequestDto);

}
