package com.cg.flightschedule.services;

import java.math.BigInteger;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flightschedule.entity.Flight;
import com.cg.flightschedule.repository.IFlightRepository;

@Service
public class FlightService implements IFlightService{

	private static final Logger log=LoggerFactory.getLogger(FlightService.class);
	
	@Autowired
	private IFlightRepository flightRepository;
	
	@Override
	@Transactional
	public Optional<Flight> viewFlight(BigInteger theFlightNumber) {
		
		log.debug("Inside viewFlightmethod by Flight Number in FlightService class");
		
		return flightRepository.findById(theFlightNumber);
	}
	
	
}
