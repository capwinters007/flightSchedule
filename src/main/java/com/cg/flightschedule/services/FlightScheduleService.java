package com.cg.flightschedule.services;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flightschedule.DTO.Airport;
import com.cg.flightschedule.DTO.Flight;
import com.cg.flightschedule.DTO.FlightSchedule;
import com.cg.flightschedule.DTO.Schedule;
import com.cg.flightschedule.repository.IAirportRepository;
import com.cg.flightschedule.repository.IFlightRepository;
import com.cg.flightschedule.repository.IFlightScheduleRepository;
import com.cg.flightschedule.repository.IScheduleRepository;

@Service
public class FlightScheduleService implements IFlightScheduleService{
	
	private static final Logger log=LoggerFactory.getLogger(FlightScheduleService.class);
	
	@Autowired
	IFlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	IFlightRepository flightRepository;
	
	@Autowired
	IScheduleRepository scheduleRepository;
	
	@Autowired
	IAirportRepository airportReposidtory;
	
	@Override
	@Transactional
	public void scheduleFlight(FlightSchedule flightSchedule) {
		
		log.debug("Inside scheduleFlight function");
		
		scheduleRepository.save(flightSchedule.getSchedule());
		
		flightScheduleRepository.save(flightSchedule);
	}
	
	@Override
	@Transactional
	public Optional<FlightSchedule> viewScheduledFlights(int id){
		
		log.debug("Inside viewScheduledFlights by id function in FlightScheduleController");
		
		Optional<FlightSchedule> flightScheduleOpt=flightScheduleRepository.findById(id);
		
		return flightScheduleOpt;
	}
	
	@Override
	@Transactional
	public List<FlightSchedule> viewScheduledFlights(Airport arrival, Airport destination, LocalDate date) {
		
		log.debug("Inside viewScheduledFlights by parameters function in FlightSchedule Service");
		
		List<FlightSchedule> FlightScheduleList=flightScheduleRepository.getFlightScheduleByAirport(arrival, destination);
		
		System.out.println(FlightScheduleList);
		List<FlightSchedule> FlightsOnScheduleList=new ArrayList<>();
	
		
		for (FlightSchedule flightSchedule : FlightScheduleList) {
			Schedule schedule=flightSchedule.getSchedule();
			System.out.println(schedule.getDepartureTime().toLocalDate());
			if(schedule.getDepartureTime().toLocalDate().equals(date)) {
				FlightsOnScheduleList.add(flightSchedule);
			}
		}
		
		return FlightsOnScheduleList;
		
				
	}
	
	@Override
	@Transactional
	public void deleteScheduledFlight(int id) {
		
		log.debug("Inside deleteScheduledFlight function in FlightScheduleService class");
		
		Optional<FlightSchedule> flightSchedule=flightScheduleRepository.findById(id);
		
		scheduleRepository.deleteById(flightSchedule.get().getSchedule().getScheduleId());
		
		flightScheduleRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void modifyScheduledFlight(FlightSchedule flightSchedule) {
		
		log.debug("Inside modifyScheduledFlight function in FlightScheduleService class");
		
		scheduleRepository.save(flightSchedule.getSchedule());
		
		flightScheduleRepository.save(flightSchedule);
		
	}
	
	@Override
	@Transactional
	public List<FlightSchedule> viewScheduledFlights(){
		
		log.debug("Inside viewScheduledFlights function in FlightScheduleService class");
		
		List<FlightSchedule> flightScheduleList=flightScheduleRepository.findAll();
		
		return flightScheduleList;
				
		
	}
	
	@Override
	@Transactional
	public String validateScheduledFlight(FlightSchedule flightSchedule){
		
		int id=flightSchedule.getScheduleFlightId();
		Flight flight=flightSchedule.getFlight();
		Schedule schedule=flightSchedule.getSchedule();
		
		
		Optional<FlightSchedule> FlightScheduleOpt=flightScheduleRepository.findById(id);
		
		if(FlightScheduleOpt.isPresent()) {
			return "Flight Schedule with this ID already exists!!";
		}
		
		Optional<Flight> FlightOpt=flightRepository.findById(flight.getFlightNumber());
		
		if(!FlightOpt.isPresent()) {
			return "No Flight with flight Number "+flight.getFlightNumber()+" exists!!";
		}
		
		
		Airport source=schedule.getSourceAirport();
		Optional<Airport> AirportSourceOpt=airportReposidtory.findById(source.getAirportCode());
		if(!AirportSourceOpt.isPresent()) {
			return "No source airport exists of code "+source.getAirportCode();
		}
		
		Airport destination=schedule.getDestinationAirport();
		Optional<Airport> AirportDestinationOpt=airportReposidtory.findById(destination.getAirportCode());
		if(!AirportDestinationOpt.isPresent()) {
			return "No destination airport exists of code "+destination.getAirportCode();
		}
		
		LocalDateTime arrivalTime=schedule.getArrivalTime();
		int status=arrivalTime.toLocalDate().compareTo(LocalDate.now());
		if(status<1) {
			return "Arrival date should be greater than present date";
		}
		
		LocalDateTime departureTime=schedule.getDepartureTime();
		int status2=departureTime.toLocalDate().compareTo(LocalDate.now());
		if(status2<1) {
			return "Departure time should be greater than present date";
		}
		
		
		return "valid data";
		
		
	}
	

}
