package com.cg.flightschedule.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airport")
public class Airport{
	
	
	@Id
	@Column(name="airportCode")
	private String airportCode;
	
	@Column(name="airportLocation")
	private String airportLocation;
	
	@Column(name="airportName")
	private String airportName;
	
	public Airport() {
		super();
	}

	public Airport(String airportCode, String airportLocation, String airportName) {
		super();
		this.airportCode = airportCode;
		this.airportLocation = airportLocation;
		this.airportName = airportName;
	}

	@Override
	public String toString() {
		return "Airport [airportCode=" + airportCode + ", airportLocation=" + airportLocation + ", airportName="
				+ airportName + "]";
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	
	
	
	
	
	
}