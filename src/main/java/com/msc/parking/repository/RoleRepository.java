package com.msc.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msc.parking.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
