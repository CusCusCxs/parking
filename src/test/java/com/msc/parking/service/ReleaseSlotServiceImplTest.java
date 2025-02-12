package com.msc.parking.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.msc.parking.dto.ReleaseRequestDto;
import com.msc.parking.dto.ReleaseResponseDto;
import com.msc.parking.entity.AvailableSlot;
import com.msc.parking.entity.Registration;
import com.msc.parking.entity.Slot;
import com.msc.parking.repository.AvailableSlotRepository;
import com.msc.parking.repository.RegistrationRepository;
import com.msc.parking.repository.SlotRepository;
import com.msc.parking.service.ReleaseSlotServiceImpl;

//TODO: Fix test case for ReleaseSlotServiceImpl
@RunWith(MockitoJUnitRunner.class)
public class ReleaseSlotServiceImplTest {
	@Mock
	RegistrationRepository registrationRepository;
	@Mock
	SlotRepository slotRepository;
	@Mock
	AvailableSlotRepository availableSlotRepository;
	@InjectMocks
	ReleaseSlotServiceImpl releaseSlotServiceImpl;

	ReleaseResponseDto releaseResponseDto;
	ReleaseRequestDto releaseRequestDto;
	Registration registration;
	Slot slot;
	AvailableSlot availableSlot;

	@Before
	public void setUp() {
		releaseResponseDto = new ReleaseResponseDto("Slot released Successfully");
		releaseRequestDto = new ReleaseRequestDto(1, LocalDate.of(2019, 8, 30), LocalDate.of(2019, 9, 02));
		registration = new Registration(1, "teuirmao", "12345", "2233445566", 16f, 7f, 2);
		slot = new Slot(1, "A1", 1);
		availableSlot = new AvailableSlot(1, LocalDate.of(2019, 8, 30), 1, "Available", 1, null);

	}

	@Test
	public void testReleaseSlots() {
		Mockito.when(registrationRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(registration));
		Mockito.when(slotRepository.findByRegistrationId(Mockito.anyInt())).thenReturn(Optional.of(slot));
		Mockito.when(availableSlotRepository.save(Mockito.any())).thenReturn(availableSlot);
		ReleaseResponseDto response = releaseSlotServiceImpl.releaseSlots(releaseRequestDto);
		Assert.assertEquals("Slot released Successfully", response.getMessage());

	}

//	@Test(expected = CommonException.class)
//	public void testReleaseSlots_1() {
//		releaseSlotServiceImpl.releaseSlots(releaseRequestDto);
//	}
}
