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
import com.cg.flightschedule.DTO.Flight;
import com.cg.flightschedule.DTO.FlightSchedule;

import com.cg.flightschedule.exception.IdException;
import com.cg.flightschedule.services.IFlightScheduleService;
import com.cg.flightschedule.services.IFlightService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/flight")
public class Controller {
	
	private static final Logger log=LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	IFlightScheduleService flightScheduleServive;
	
	@Autowired
	IFlightService flightService;

	@PostMapping(path="/add")
	public String add(@RequestBody FlightSchedule flightSchedule)
	{
		
		
		
		log.debug("Inside add method in controller class");
		
		String validate=flightScheduleServive.validateScheduledFlight(flightSchedule);
		
		if("valid data".equals(validate)) {
			Flight flight=flightService.getFlight(flightSchedule.getFlight().getFlightNumber());
			flightSchedule.setAvailableSeats(flight.getSeatNumber());
			flightScheduleServive.scheduleFlight(flightSchedule);
			validate="Added successfully";
		}
		
		
		
		
		System.out.println(flightSchedule);
		
		return validate;
		
		
	}
	
	@GetMapping("/viewAsPerAirportAttributes")
	public List<FlightSchedule> getFlightOnDate(@RequestParam("source")String source,@RequestParam("destination") String destination,@RequestParam("date")String Date){
		
		
		Airport airport1=new Airport("BOM",null,null);
		Airport airport2=new Airport("NDL",null,null);
		
		LocalDate date=LocalDate.of(2020, 05, 10);
		
		List<FlightSchedule> FlightScheduleList=flightScheduleServive.viewScheduledFlights(airport2,airport1,date);
		
		return FlightScheduleList;
	}
	@GetMapping("/deleteFlightSchedule")
	public String delete(@RequestParam("id") int id) throws IdException {
		
		log.debug("Inside delete method in controller class");
		
		Optional<FlightSchedule> flightScheduleOpt=flightScheduleServive.viewScheduledFlights(id);
		
		if(!flightScheduleOpt.isPresent()) {
			throw new IdException();
		}
		
		flightScheduleServive.deleteScheduledFlight(id);
		
		return "Deleted succesfully";
	}
	
	@PostMapping("/update")
	public String update(@RequestBody FlightSchedule flightSchedule) {
		
		log.debug("Inside update method in controller class");
		
		System.out.println(flightSchedule);
		System.out.println(flightSchedule.getCost());
		
		flightScheduleServive.modifyScheduledFlight(flightSchedule);
		
		return "updated";
		
	}
	@GetMapping("/viewById")
	public FlightSchedule getFlightScheduleById(@RequestParam("id")int id) throws IdException{
		
		log.debug("Inside getFlightScheduleById in controller class");
		
		Optional<FlightSchedule> flightScheduleOpt=flightScheduleServive.viewScheduledFlights(id);
		
		
		
		if(!flightScheduleOpt.isPresent()) {
			throw new IdException();
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
