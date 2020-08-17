package com.cg.flightschedule.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flightschedule.entity.Airport;
import com.cg.flightschedule.repository.IAirportRepository;

@Service
public class  AirportService  implements IAirportService{
		
		@Autowired
		private IAirportRepository  airportRepository;
		
		@Override
		@Transactional
		public Optional<Airport> viewAirport(String Code) {
	
			Optional<Airport> airportObj=airportRepository.findById(Code);
			
			return airportObj;
		}

}
