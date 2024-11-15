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
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PatientDetailsDTO;
import com.hexaware.amazecare.DTO.UsersDTO;
import com.hexaware.amazecare.Exception.AppointmentNotFoundException;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.DuplicateDoctorFoundException;
import com.hexaware.amazecare.Exception.PatientNotFoundException;
import com.hexaware.amazecare.Exception.RecordsNotFoundException;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Service.AdminServiceImpl;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	AdminServiceImpl service;
	
	@PutMapping("/admin/editProfile")
	public ResponseEntity<UsersDTO> editAdmin(@RequestBody UsersDTO d){
		
		UsersDTO admin = service.editAdmin(d);
		
		return new ResponseEntity<>(admin,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/admin/addDoctor")
	public ResponseEntity<DoctorDetailsDTO> adddoctor(@RequestBody DoctorDTO d) throws DuplicateDoctorFoundException{
		
		DoctorDetailsDTO doc = service.addadoctor(d);
		if(doc==null) {
			throw new DuplicateDoctorFoundException("Given Email is already used for a doctor!!");
		}
		
		return new ResponseEntity<>(doc,HttpStatus.CREATED);
		
	}
	
	 @GetMapping("/getDoctorSpecialties")
	    public List<String> getDistinctSpecialties() {
	        return service.getDistinctSpecialties();
	    }
	
	@GetMapping("/admin/getDoctors/{name}")
	public ResponseEntity<List<DoctorDetailsDTO>> viewdoctors(@PathVariable String name) throws DoctorNotFoundException{
		List<DoctorDetailsDTO> doc=service.viewDoctorsByName(name);
		if(doc==null) {
			throw new DoctorNotFoundException("No Doctors Found!");
		}
		return new ResponseEntity<>(doc,HttpStatus.OK);
	}
	
	@GetMapping("/admin/getAllDoctors")
	public ResponseEntity<List<DoctorDetailsDTO>> viewdoctor() throws DoctorNotFoundException{
		List<DoctorDetailsDTO> doc=service.viewAllDoctors();
		if(doc==null) {
			throw new DoctorNotFoundException("No Doctors Found!");
		}
		return new ResponseEntity<>(doc,HttpStatus.OK);
	} 
	
	@GetMapping("/admin/getPatients/{name}")
	public ResponseEntity<List<PatientDetailsDTO>> viewPatients(@PathVariable String name) throws PatientNotFoundException{
		List<PatientDetailsDTO> patients=service.viewPatientsByName(name);
		if(patients==null) {
			throw new PatientNotFoundException("No Patients Found!");
		}
		return new ResponseEntity<>(patients,HttpStatus.OK);
	}
	
	@GetMapping("/admin/getAllPatients")
	public ResponseEntity<List<PatientDetailsDTO>> viewPatient() throws PatientNotFoundException{
		List<PatientDetailsDTO> patients=service.viewAllPatients();
		if(patients==null) {
			throw new PatientNotFoundException("No Patients Found!");
		}
		return new ResponseEntity<>(patients,HttpStatus.OK);
	} 

	@GetMapping("/admin/getAllAppointments")
	public ResponseEntity<List<AppointmentDetailsDTO>> viewAppointment() throws AppointmentNotFoundException{
		List<AppointmentDetailsDTO> Appointments=service.viewAllAppointments();
		if(Appointments==null) {
			throw new AppointmentNotFoundException("No Appointments Found!");
		}
		return new ResponseEntity<>(Appointments,HttpStatus.OK);
	} 

	@GetMapping("/admin/getAllRecords")
	public ResponseEntity<List<MedicalRecordDetailsDTO>> viewRecord() throws RecordsNotFoundException{
		List<MedicalRecordDetailsDTO> Records=service.viewAllRecords();
		if(Records==null) {
			throw new RecordsNotFoundException("No MedicalRecords Found!");
		}
		return new ResponseEntity<>(Records,HttpStatus.OK);
	} 
	
	@DeleteMapping("/admin/deleteappointment/{appointmentid}")
	public ResponseEntity<String> deleteappointmnet(@PathVariable int appointmentid) throws AppointmentNotFoundException{
		
		String r= service.deleteaappointment(appointmentid);
		if (r==null) {
			throw new AppointmentNotFoundException("No appointment found");
		}
		return new ResponseEntity<>(r,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/deletedoctorbyid/{doctorid}")
	public ResponseEntity<String> deletedoctorbyid(@PathVariable int doctorid) throws DoctorNotFoundException{
		String doctor = service.deletedoctorbyid(doctorid);
		if(doctor==null){
			throw new DoctorNotFoundException("Doctor not found");
		}
		return new ResponseEntity<>(doctor,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/deletepatientbyid/{patientid}")
	public ResponseEntity<String> deletepatientbyid(@PathVariable int patientid) throws PatientNotFoundException{
		String patient = service.deletepatientbyid(patientid);
		if(patient==null){
			throw new PatientNotFoundException("patient not found");
		}
		return new ResponseEntity<>(patient,HttpStatus.OK);
		
	}
	@DeleteMapping("/admin/deleterecordbyid/{recordid}")
	public ResponseEntity<String> deleterecordbyid(@PathVariable int recordid) throws RecordsNotFoundException{
		String record = service.deleterecordbyid(recordid);
		if(record==null){
			throw new RecordsNotFoundException("record not found");
		}
		return new ResponseEntity<>(record,HttpStatus.OK);
		
	}
	
	
}







