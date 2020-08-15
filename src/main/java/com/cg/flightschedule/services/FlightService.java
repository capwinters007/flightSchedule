package com.cg.flightschedule.services;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flightschedule.DTO.Flight;
import com.cg.flightschedule.repository.IFlightRepository;

@Service
public class FlightService implements IFlightService{

	@Autowired
	private IFlightRepository flightRepository;
	
	@Override
	@Transactional
	public Flight getFlight(BigInteger id) {
		
		Optional<Flight> opt=flightRepository.findById(id);
		return opt.get();
		
	}
	
	
}
