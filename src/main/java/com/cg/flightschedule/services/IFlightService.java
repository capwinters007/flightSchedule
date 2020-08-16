package com.cg.flightschedule.services;

import java.math.BigInteger;
import java.util.Optional;

import com.cg.flightschedule.DTO.Flight;

public interface IFlightService {

	public Optional<Flight> viewFlight(BigInteger id);
	
}
