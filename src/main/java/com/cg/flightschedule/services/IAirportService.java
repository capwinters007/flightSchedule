package com.cg.flightschedule.services;

import java.util.Optional;

import com.cg.flightschedule.DTO.Airport;

public interface IAirportService {
	
	public Optional<Airport> viewAirport(String code);

}
