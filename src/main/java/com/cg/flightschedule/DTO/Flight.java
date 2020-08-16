package com.cg.flightschedule.DTO;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {
	
	
	@Id
	@Column(name="flightNumber")
	private BigInteger flightNumber;
	
	@Column(name="carrierName")
	private String carrierName;
	
	@Column(name="flightModel")
	private String flightModel;
	
	@Column(name="seatCapacity")
	private int seatNumber;
	
	
	public Flight() {
		super();
	}

	public Flight(BigInteger flightNumber, String carrierName, String flightModel, int seatNumber) {
		super();
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatNumber = seatNumber;
	}

	public BigInteger getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(BigInteger flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getFlightModel() {
		return flightModel;
	}

	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", carrierName=" + carrierName + ", flightModel=" + flightModel
				+ ", seatNumber=" + seatNumber + "]";
	}
	
	

}
