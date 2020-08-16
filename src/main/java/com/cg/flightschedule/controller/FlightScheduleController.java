package com.cg.flightschedule.controller;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flightschedule.DTO.Airport;
import com.cg.flightschedule.DTO.FlightSchedule;

import com.cg.flightschedule.exception.FlightScheduleNotFoundException;
import com.cg.flightschedule.services.IAirportService;
import com.cg.flightschedule.services.IFlightScheduleService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/flight")
public class FlightScheduleController {
	
	private static final Logger log=LoggerFactory.getLogger(FlightScheduleController.class);
	
	@Autowired
	IFlightScheduleService flightScheduleServive;
	
	@Autowired
	IAirportService airportService;
	

	@PostMapping(path="/add")
	public String add(@RequestBody FlightSchedule flightSchedule)
	{
		log.debug("Inside add method in controller class");
		System.out.println("in add");
		
		String validate=flightScheduleServive.validateScheduledFlight(flightSchedule);
		
		if("valid data".equals(validate)) {
			flightScheduleServive.scheduleFlight(flightSchedule);
			validate="Added successfully";
		}
		return validate;
	}
	
	@GetMapping("/viewByAirport")
	public List<FlightSchedule> getFlightOnDate(@RequestParam("source")String source,@RequestParam("destination") String destination,@RequestParam("date")String Date) throws FlightScheduleNotFoundException{
		
		
		Airport airport1=airportService.viewAirport(source).get();
		Airport airport2=airportService.viewAirport(destination).get();
		LocalDate date=LocalDate.parse(Date);
		
		if(date.compareTo(LocalDate.now())<1) {
			throw new FlightScheduleNotFoundException();
		}
		
		List<FlightSchedule> FlightScheduleList=flightScheduleServive.viewScheduledFlights(airport1,airport2,date);
		
		if(FlightScheduleList.isEmpty()) {
			throw new FlightScheduleNotFoundException();
		}
		
		return FlightScheduleList;
	}
	@GetMapping("/deleteFlightSchedule")
	public String delete(@RequestParam("id") int id) throws FlightScheduleNotFoundException {
		
		log.debug("Inside delete method in controller class");
		
		Optional<FlightSchedule> flightScheduleOpt=flightScheduleServive.viewScheduledFlights(id);
		
		if(!flightScheduleOpt.isPresent()) {
			throw new FlightScheduleNotFoundException();
		}
		
		flightScheduleServive.deleteScheduledFlight(id);
		
		return "Deleted succesfully";
	}
	
	@PostMapping("/update")
	public String update(@RequestBody FlightSchedule flightSchedule) {
		
		log.debug("Inside update method in controller class");
		
		flightScheduleServive.modifyScheduledFlight(flightSchedule);
		
		return "updated";
		
	}
	@GetMapping("/viewById")
	public FlightSchedule getFlightScheduleById(@RequestParam("id")int id) throws FlightScheduleNotFoundException{
		
		log.debug("Inside getFlightScheduleById in controller class");
		
		Optional<FlightSchedule> flightScheduleOpt=flightScheduleServive.viewScheduledFlights(id);
		
		if(!flightScheduleOpt.isPresent()) {
			throw new FlightScheduleNotFoundException();
		}
		
		FlightSchedule flightSchedule=flightScheduleOpt.get();
		
		return flightSchedule;
	}
	
	@GetMapping("/viewAll")
	public List<FlightSchedule> getAllFlightSchedule(){
		System.out.println("in view all");
		return flightScheduleServive.viewScheduledFlights();
	}
	
}
