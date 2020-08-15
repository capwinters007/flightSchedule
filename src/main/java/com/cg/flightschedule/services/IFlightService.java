package com.cg.flightschedule.services;

import java.math.BigInteger;

import com.cg.flightschedule.DTO.Flight;

public interface IFlightService {

	public Flight getFlight(BigInteger id);
	
}
