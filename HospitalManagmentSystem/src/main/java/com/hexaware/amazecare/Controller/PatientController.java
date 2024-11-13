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
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.Exception.AppointmentNotFoundException;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.PatientNotFoundException;
import com.hexaware.amazecare.Exception.RecordsNotFoundException;
import com.hexaware.amazecare.Service.PatientService;



@RestController
@RequestMapping("/api/patient")
public class PatientController {

	/*
	 * login-
	 * logout-
	 * register- insert done
	
	 * book appointment {doctorid}done
	 * view appointments done
	 * view medical history done
	 * update profile done
	 * Reschedule appointment done
	 * Cancel appointment done
	 * 
	 * */
	@Autowired
	PatientService ps;
	
	

	@PostMapping("/register")
	public ResponseEntity<PatientDTO> register(@RequestBody PatientDTO p) {
	    PatientDTO savedData = ps.savedata(p); 
	    return new ResponseEntity<>(savedData, HttpStatus.OK); // Corrected syntax
	}
	
	@PutMapping("/updateprofile/{id}")
	public ResponseEntity<PatientDTO> updateProfile(@PathVariable int id, @RequestBody PatientDTO p) throws PatientNotFoundException {
	    PatientDTO updatedProfile = ps.updateprofile(id, p);
	    if (updatedProfile == null) {
	        throw new PatientNotFoundException("Patient does not exist");
	    }
	    return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
	}
	
	@GetMapping("/getappointments/{patientid}")
	public ResponseEntity<List<AppointmentDTO>> findallappointments(@PathVariable int patientid) throws AppointmentNotFoundException{
		
		List<AppointmentDTO> appointments = ps.getpatientappoints(patientid);
		
		if(appointments.isEmpty()) {
			throw new AppointmentNotFoundException("Appointments for patient not found");
		}
		return new ResponseEntity<>(appointments,HttpStatus.OK);
		
	}
	
	@GetMapping("/getmedicalrecords/{patientid}")
	public ResponseEntity<List<MedicalRecordDTO>> findallmedicalrecords(@PathVariable int patientid)throws RecordsNotFoundException{
		
		List<MedicalRecordDTO> records = ps.getpatientmedicalrecords(patientid) ;
		if (records.isEmpty()) {
			throw new RecordsNotFoundException("patient record not found");
		}
		return new ResponseEntity<>(records,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAvailableDoctors/{speciality}")
	public ResponseEntity<List<DoctorDTO>> getAvailableDoctors(@PathVariable String speciality)throws RecordsNotFoundException, DoctorNotFoundException{
		
		List<DoctorDTO> doctors = ps.getAvailableDoctors(speciality) ;
		if (doctors==null) {
			throw new DoctorNotFoundException("doctor record not found");
		}
		return new ResponseEntity<>(doctors,HttpStatus.OK);
		
	}
	
	/*@DeleteMapping("/deleteappointment/{appointmentid}")
	public ResponseEntity<String> deleteappointmnet(@PathVariable int appointmentid) throws AppointmentNotFoundException{
		
		String r= ps.deleteaappointment(appointmentid);
		if (r==null) {
			throw new AppointmentNotFoundException("No appointment found");
		}
		return new ResponseEntity<>(r,HttpStatus.OK);
		
	}*/
	
	@PostMapping("/bookappointment/{patientid}/{doctorid}")
	public ResponseEntity<AppointmentDTO> bookappointmnet(@PathVariable int patientid,@PathVariable int doctorid,@RequestBody AppointmentDTO a) throws DoctorNotFoundException, PatientNotFoundException{
		
		AppointmentDTO appointment = ps.bookanappointment(a,patientid,doctorid);
		if (appointment==null) {
			throw new DoctorNotFoundException("Doctor not found");
		}
		
		return new ResponseEntity<>(appointment,HttpStatus.CREATED);
	}
	
	@PutMapping("/rescheduleAppointmentByPatient/{appointmentid}/{date}/{time}")
	public ResponseEntity<AppointmentDTO> rescheduleAppointmentByPatient( 
	        @PathVariable int appointmentid, 
	        @PathVariable LocalDate date, 
	        @PathVariable String time) throws AppointmentNotFoundException {

	    LocalTime appointtime = LocalTime.parse(time);
	    AppointmentDTO updated = ps.rescheduleAppointmentByPatient(appointmentid, date, appointtime);
	    if (updated == null) {
	        throw new AppointmentNotFoundException("Appointment does not exist!!");
	    }
	    return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	
	



}


















