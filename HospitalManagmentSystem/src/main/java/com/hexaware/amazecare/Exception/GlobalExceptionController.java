package com.hexaware.amazecare.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {
	
	

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<errorDetails> CustomerNotFound(DoctorNotFoundException e) {
		
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),"location not found",HttpStatus.NOT_FOUND.toString());
	
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<errorDetails> CustomerNotFound(AppointmentNotFoundException e) {
		
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),"location not found",HttpStatus.NOT_FOUND.toString());
	
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<errorDetails> PatientNotFound(PatientNotFoundException p){
		errorDetails error = new errorDetails(LocalDateTime.now(),p.getMessage(),"patient not found",HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<errorDetails> handelallexceptions(Exception e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getMessage(),path,"Exception");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<errorDetails>
	 * AllException(Exception e) {
	 * 
	 * errorDetails error= new
	 * errorDetails(LocalDateTime.now(),e.getClass().getSimpleName() + ": " +
	 * e.getMessage(),"UnExpected Error",HttpStatus.INTERNAL_SERVER_ERROR.toString()
	 * );
	 * 
	 * return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	
}
