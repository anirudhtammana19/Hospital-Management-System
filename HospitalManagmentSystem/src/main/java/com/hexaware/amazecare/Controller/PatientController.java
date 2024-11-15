package com.hexaware.amazecare.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PatientDetailsDTO;
import com.hexaware.amazecare.Exception.AppointmentNotFoundException;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.DuplicatePatientFoundException;
import com.hexaware.amazecare.Exception.PatientNotFoundException;
import com.hexaware.amazecare.Exception.RecordsNotFoundException;
import com.hexaware.amazecare.Service.IPatientService;
import com.hexaware.amazecare.Service.PatientServiceImpl;



@RestController
@RequestMapping("/api")
public class PatientController {


	@Autowired
	IPatientService service;
	
	

	@PostMapping("/register")
	public ResponseEntity<PatientDetailsDTO> register(@RequestBody PatientDTO p) throws DuplicatePatientFoundException {
	    PatientDetailsDTO savedData = service.savedata(p); 
	    if(savedData==null) {
	    	throw new DuplicatePatientFoundException("Given Email already has an account!!");
	    }
	    return new ResponseEntity<>(savedData, HttpStatus.CREATED); 
	}
	
	//View Profile
		@GetMapping("/patient/viewProfile")
		public ResponseEntity<PatientDetailsDTO> viewDoctorProfile() throws DoctorNotFoundException{
			
			PatientDetailsDTO patient=service.viewPatientProfile();
			if(patient==null) {
				throw new DoctorNotFoundException("Doctor does not exist!!");
			}
			return new ResponseEntity<>(patient,HttpStatus.OK);
		}
	
	@PutMapping("/patient/updateprofile/{id}")
	public ResponseEntity<PatientDetailsDTO> updateProfile(@PathVariable int id, @RequestBody PatientDTO p) throws PatientNotFoundException {
	    PatientDetailsDTO updatedProfile = service.updateprofile(id, p);
	    if (updatedProfile == null) {
	        throw new PatientNotFoundException("Patient does not exist");
	    }
	    return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
	}
	
	@GetMapping("/patient/getappointments/{patientid}")
	public ResponseEntity<List<AppointmentDetailsDTO>> findallappointments(@PathVariable int patientid) throws AppointmentNotFoundException{
		
		List<AppointmentDetailsDTO> appointments = service.getpatientappoints(patientid);
		
		if(appointments.isEmpty()) {
			throw new AppointmentNotFoundException("Appointments for patient not found");
		}
		return new ResponseEntity<>(appointments,HttpStatus.OK);
		
	}
	
	@GetMapping("/patient/getmedicalrecords/{patientid}")
	public ResponseEntity<List<MedicalRecordDetailsDTO>> findallmedicalrecords(@PathVariable int patientid)throws RecordsNotFoundException{
		
		List<MedicalRecordDetailsDTO> records = service.getpatientmedicalrecords(patientid) ;
		if (records.isEmpty()) {
			throw new RecordsNotFoundException("patient record not found");
		}
		return new ResponseEntity<>(records,HttpStatus.OK);
		
	}
	
	@GetMapping("/patient/getAvailableDoctors/{speciality}")
	public ResponseEntity<List<DoctorDetailsDTO>> getAvailableDoctors(@PathVariable String speciality)throws RecordsNotFoundException, DoctorNotFoundException{
		
		List<DoctorDetailsDTO> doctors = service.getAvailableDoctors(speciality) ;
		if (doctors==null) {
			throw new DoctorNotFoundException("doctor record not found");
		}
		return new ResponseEntity<>(doctors,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/patient/bookappointment/{patientid}/{doctorid}")
	public ResponseEntity<AppointmentDetailsDTO> bookappointmnet(@PathVariable int patientid,@PathVariable int doctorid,@RequestBody AppointmentDTO a) throws DoctorNotFoundException, PatientNotFoundException{
		
		AppointmentDetailsDTO appointment = service.bookanappointment(a,patientid,doctorid);
		if (appointment==null) {
			throw new DoctorNotFoundException("Doctor not found");
		}
		
		return new ResponseEntity<>(appointment,HttpStatus.CREATED);
	}
	
	@PutMapping("/patient/rescheduleAppointment/{appointmentid}/{date}/{time}")
	public ResponseEntity<AppointmentDetailsDTO> rescheduleAppointmentByPatient( 
	        @PathVariable int appointmentid, 
	        @PathVariable LocalDate date, 
	        @PathVariable String time) throws AppointmentNotFoundException {

	    LocalTime appointtime = LocalTime.parse(time);
	    AppointmentDetailsDTO updated = service.rescheduleAppointmentByPatient(appointmentid, date, appointtime);
	    if (updated == null) {
	        throw new AppointmentNotFoundException("Appointment does not exist!!");
	    }
	    return new ResponseEntity<>(updated, HttpStatus.OK);
	}

}


















