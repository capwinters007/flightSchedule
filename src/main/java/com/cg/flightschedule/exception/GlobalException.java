package com.cg.flightschedule.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	 static final Logger log=LoggerFactory.getLogger(GlobalException.class);

	
	@ExceptionHandler({IdException.class})
	public String handleException(Exception ex) {
		String msg="No data found for this id";
		
		log.debug(msg);
		
		return msg;
	}

}
