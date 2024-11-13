package com.hexaware.amazecare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.Exception.AppointmentNotFoundException;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.PatientNotFoundException;
import com.hexaware.amazecare.Exception.RecordsNotFoundException;
import com.hexaware.amazecare.Model.Doctor;
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
	
	@PostMapping("/doctor/add")
	public ResponseEntity<DoctorDTO> adddoctor(@RequestBody DoctorDTO d){
		
		DoctorDTO doc = as.addadoctor(d);
		
		return new ResponseEntity<>(doc,HttpStatus.CREATED);
		
	}
	@GetMapping("/getAllDoctors")
	public ResponseEntity<List<DoctorDTO>> viewdoctor(){
		List<DoctorDTO> doc=as.viewAllDoctors();
		return new ResponseEntity<>(doc,HttpStatus.OK);
	} 
	
	@GetMapping("/getAllPatients")
	public ResponseEntity<List<PatientDTO>> viewPatient(){
		List<PatientDTO> patients=as.viewAllPatients();
		return new ResponseEntity<>(patients,HttpStatus.OK);
	} 

	@GetMapping("/getAllAppointments")
	public ResponseEntity<List<AppointmentDTO>> viewAppointment(){
		List<AppointmentDTO> Appointments=as.viewAllAppointments();
		return new ResponseEntity<>(Appointments,HttpStatus.OK);
	} 

	@GetMapping("/getAllRecords")
	public ResponseEntity<List<MedicalRecordDTO>> viewRecord(){
		List<MedicalRecordDTO> Records=as.viewAllRecords();
		return new ResponseEntity<>(Records,HttpStatus.OK);
	} 
	
	@DeleteMapping("/deleteappointment/{appointmentid}")
	public ResponseEntity<String> deleteappointmnet(@PathVariable int appointmentid) throws AppointmentNotFoundException{
		
		String r= as.deleteaappointment(appointmentid);
		if (r==null) {
			throw new AppointmentNotFoundException("No appointment found");
		}
		return new ResponseEntity<>(r,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletedoctorbyid/{doctorid}")
	public ResponseEntity<String> deletedoctorbyid(@PathVariable int doctorid) throws DoctorNotFoundException{
		String doctor = as.deletedoctorbyid(doctorid);
		if(doctor==null){
			throw new DoctorNotFoundException("Doctor not found");
		}
		return new ResponseEntity<>(doctor,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletepatientbyid/{patiendid}")
	public ResponseEntity<String> deletepatientbyid(@PathVariable int patientid) throws PatientNotFoundException{
		String patient = as.deletedoctorbyid(patientid);
		if(patient==null){
			throw new PatientNotFoundException("patient not found");
		}
		return new ResponseEntity<>(patient,HttpStatus.OK);
		
	}
	@DeleteMapping("/deleterecordbyid/{recordid}")
	public ResponseEntity<String> deleterecordbyid(@PathVariable int recordid) throws RecordsNotFoundException{
		String record = as.deletedoctorbyid(recordid);
		if(record==null){
			throw new RecordsNotFoundException("record not found");
		}
		return new ResponseEntity<>(record,HttpStatus.OK);
		
	}
	
	
}







