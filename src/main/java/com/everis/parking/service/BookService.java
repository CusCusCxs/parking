package com.everis.parking.service;

import com.everis.parking.dto.BookRequestDto;
import com.everis.parking.dto.BookResponseDto;

public interface BookService {
	
	
	BookResponseDto bookSlot(BookRequestDto bookRequestDto);
	BookResponseDto doRaffle();

}
