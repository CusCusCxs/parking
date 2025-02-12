
package com.msc.parking.service;

import com.msc.parking.dto.BookRequestDto;
import com.msc.parking.dto.BookResponseDto;

public interface BookService {
	
	
	BookResponseDto bookSlot(BookRequestDto bookRequestDto);
	BookResponseDto doRaffle();

}
