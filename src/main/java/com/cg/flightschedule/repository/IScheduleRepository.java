package com.cg.flightschedule.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.flightschedule.entity.Schedule;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Integer>{

	
}
