package com.everis.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.parking.dto.BookRequestDto;
import com.everis.parking.dto.BookResponseDto;
import com.everis.parking.service.BookService;

/**
 * Esta classe representa o controlador para reserva de vagas de estacionamento.
 * Ele lida com as solicitações HTTP relacionadas à reserva de vagas e realização de sorteios.
 */
@RestController
@RequestMapping("/api")
public class BookSlotController {

	@Autowired
	BookService bookService;

	/**
	 * Endpoint para reserva de vagas de estacionamento.
	 * 
	 * @param bookRequestDto O DTO contendo os detalhes da reserva.
	 * @return A entidade de resposta com os detalhes da vaga reservada.
	 */
	@PostMapping("/bookSlot")
	public ResponseEntity<BookResponseDto> bookSlots(@RequestBody BookRequestDto bookRequestDto) {
		return new ResponseEntity<>(bookService.bookSlot(bookRequestDto), HttpStatus.CREATED);
	}

	/**
	 * Endpoint for performing a raffle to select a winner.
	 * 
	 * @return The response entity with the winner's details.
	 */
	@PostMapping("/doRaffle")
	public ResponseEntity<BookResponseDto> doRaffle() {
		return new ResponseEntity<>(bookService.doRaffle(), HttpStatus.CREATED);
	}

}
