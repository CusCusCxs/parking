package com.msc.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msc.parking.entity.RequestSlot;
@Repository
public interface RequestSlotRepository extends JpaRepository<RequestSlot, Integer>{
	
	List<RequestSlot> findByRequestDate(LocalDate localDate);

}
