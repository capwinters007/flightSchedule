package com.cg.flightschedule.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.flightschedule.DTO.Flight;


@Repository
public interface IFlightRepository extends JpaRepository<Flight, BigInteger> {
	
	

}
