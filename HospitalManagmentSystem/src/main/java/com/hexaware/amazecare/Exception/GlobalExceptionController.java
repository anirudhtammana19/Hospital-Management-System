package com.hexaware.amazecare.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(DuplicateDoctorFoundException.class)
	public ResponseEntity<errorDetails> DuplicateDoctorFound(DuplicateDoctorFoundException e,HttpServletRequest hr) {
		
		String path = hr.getRequestURI();
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
	
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicatePatientFoundException.class)
	public ResponseEntity<errorDetails> DuplicatePatientFound(DuplicatePatientFoundException e,HttpServletRequest hr) {
		
		String path = hr.getRequestURI();
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
	
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<errorDetails> DoctorNotFound(DoctorNotFoundException e,HttpServletRequest hr) {
		
		String path = hr.getRequestURI();
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
	
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<errorDetails> AppointmentNotFound(AppointmentNotFoundException e,HttpServletRequest hr) {

		String path = hr.getRequestURI();
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
	
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AppointmentCancelledException.class)
	public ResponseEntity<errorDetails> AppointmentCancelled(AppointmentCancelledException e,HttpServletRequest hr) {
		String path = hr.getRequestURI();
		errorDetails error= new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.CONFLICT.toString());
	
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<errorDetails> PatientNotFound(PatientNotFoundException e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PrescriptionsNotFoundException.class)
	public ResponseEntity<errorDetails> PrescriptionsNotFound(PrescriptionsNotFoundException e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RecordsNotFoundException.class)
	public ResponseEntity<errorDetails> RecordsNotFound(RecordsNotFoundException e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getMessage(),path,HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<errorDetails> handelallexceptions(Exception e,HttpServletRequest hr){
		String path = hr.getRequestURI();
		errorDetails error = new errorDetails(LocalDateTime.now(),e.getClass().getName()+":"+e.getMessage(),path,"Exception");
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
