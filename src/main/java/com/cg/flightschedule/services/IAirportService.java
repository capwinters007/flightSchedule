package com.cg.flightschedule.services;

import java.util.Optional;

import com.cg.flightschedule.entity.Airport;

public interface IAirportService {
	
	public Optional<Airport> viewAirport(String code);

}
