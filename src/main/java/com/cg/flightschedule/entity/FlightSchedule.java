package com.cg.flightschedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="flightschedule")
public class FlightSchedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="scheduleFlightId")
	private int scheduleFlightId;

	
	@OneToOne
	@JoinColumn(name="flight")
	private Flight flight;
	
	@Column(name="availableSeats")
	private int availableSeats;
	
	@OneToOne
	@JoinColumn(name="schedule")
	private Schedule schedule;
	
	@Column(name="cost")
	private double cost;
	
	
	public FlightSchedule() {
		super();
	}

	public FlightSchedule(int scheduleFlightId, Flight flight, int availableSeats, Schedule schedule,double cost) {
		super();
		this.scheduleFlightId = scheduleFlightId;
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
		this.cost=cost;
	}

	public int getScheduleFlightId() {
		return scheduleFlightId;
	}

	public void setScheduleFlightId(int scheduleFlightId) {
		this.scheduleFlightId = scheduleFlightId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "FlightSchedule [scheduleFlightId=" + scheduleFlightId + ", flight=" + flight + ", availableSeats="
				+ availableSeats + ", schedule=" + schedule + "]";
	}
	
	
}
