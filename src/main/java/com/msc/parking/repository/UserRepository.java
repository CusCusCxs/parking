package com.msc.parking.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msc.parking.entity.Registration;

@Repository
public interface UserRepository extends JpaRepository<Registration, Integer>{

	Optional<Registration> findByMobileNumber(String mobileNumber);

}
