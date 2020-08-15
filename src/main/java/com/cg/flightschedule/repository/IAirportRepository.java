package com.cg.flightschedule.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.flightschedule.DTO.Airport;

@Repository
public interface IAirportRepository extends JpaRepository<Airport, String>{
	

}
