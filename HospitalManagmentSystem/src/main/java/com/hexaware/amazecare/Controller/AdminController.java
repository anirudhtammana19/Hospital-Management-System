package com.hexaware.amazecare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.Service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	/*
	 *edit profile 
	 * 
	 * add doctor
	 * update doctor
	 * delete doctor
	 * search doctor
	 * view doctor's appointments
	 * 
	 * 
	 * add patient
	 * update patient
	 * delete patient
	 * search patient
	 * view patient records
	 * 
	 * add appointment
	 * cancel appointment
	 * reschedule appointment
	 * accept or deny appointment
	 * 
	 * add,edit,delete prescription
	 * 
	 * 
	 * 
	 * */
	@Autowired
	AdminService as;
	
	@PostMapping("/adddoc")
	public ResponseEntity<DoctorDTO> adddoctor(@RequestBody DoctorDTO d){
		
		DoctorDTO doc = as.addadoctor(d);
		
		return new ResponseEntity<>(doc,HttpStatus.CREATED);
		
	}
}
