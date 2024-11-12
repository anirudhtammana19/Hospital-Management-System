package com.hexaware.amazecare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	/*
	 * login,logout--
	 * edit profile
	 * view appointments
	 * view patient medical records
	 * Accept appointment
	 * Deny Appointment
	 * do consultation -insert medical record,prescription
	 * Rescheduled appointments
	 * cancel appointments
	 * modify records & prescription
	 * 
	 * */
	
	@Autowired
	DoctorService service;
	
	@PutMapping("/editProfile/{id}")
	public ResponseEntity<DoctorDTO> editDoctor(@PathVariable int doctorid,@RequestBody DoctorDTO doc){
		
		DoctorDTO updated=service.editDoctorProfile(doctorid,doc);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
}
