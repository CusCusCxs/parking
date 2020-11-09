package com.everis.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.parking.entity.RequestSlot;
@Repository
public interface RequestSlotRepository extends JpaRepository<RequestSlot, Integer>{
	
	List<RequestSlot> findByRequestDate(LocalDate localDate);

}
