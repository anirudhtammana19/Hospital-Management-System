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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PrescriptionDTO;
import com.hexaware.amazecare.Exception.AppointmentNotFoundException;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.PrescriptionsNotFoundException;
import com.hexaware.amazecare.Exception.RecordsNotFoundException;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
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
	 * modify records & prescription
	 * 
	 * */
	
	@Autowired
	DoctorService service;
	
	//Edit Profile
	@PutMapping("/editProfile/{doctorid}")
	public ResponseEntity<DoctorDTO> editDoctor(@PathVariable int doctorid,@RequestBody DoctorDTO doc) throws DoctorNotFoundException{
		
		DoctorDTO updated=service.editDoctorProfile(doctorid,doc);
		if(updated==null) {
			throw new DoctorNotFoundException("Doctor does not exist!!");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	//View Profile
	@GetMapping("/viewProfile/{doctorid}")
	public ResponseEntity<DoctorDTO> viewDoctorProfile(@PathVariable int doctorid) throws DoctorNotFoundException{
		
		DoctorDTO doctor=service.viewDoctorProfile(doctorid);
		if(doctor==null) {
			throw new DoctorNotFoundException("Doctor does not exist!!");
		}
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}
	
	//View Appointments
	@GetMapping("/viewAppointments/{doctorid}")
	public ResponseEntity<List<AppointmentDTO>> viewDoctorAppointments(@PathVariable int doctorid) throws AppointmentNotFoundException{
		
		List<AppointmentDTO> appointments=service.viewDoctorAppointments(doctorid);
		if(appointments.isEmpty()) {
			throw new AppointmentNotFoundException("Doctor does not any Appointments!!");
		}
		return new ResponseEntity<>(appointments,HttpStatus.OK);
	}
	
	//View Patient History
	@GetMapping("/viewPatientHistory/{patientid}")
	public ResponseEntity<List<MedicalRecordDTO>> viewPatientRecords(@PathVariable int patientid) throws RecordsNotFoundException{
		
		List<MedicalRecordDTO> records=service.viewPatientMedicalRecords(patientid);
		if(records.isEmpty()) {
			throw new RecordsNotFoundException("Patient does not any Medical Records!!");
		}
		return new ResponseEntity<>(records,HttpStatus.OK);
	}
	
	//Accept Appointment
	@PutMapping("/acceptAppointment/{doctorid},{appointmentid}")
	public ResponseEntity<AppointmentDTO> acceptAppointment(@PathVariable int doctorid,@PathVariable int appointmentid) throws AppointmentNotFoundException{
		
		AppointmentDTO updated=service.acceptAppointment(doctorid,appointmentid);
		if(updated==null) {
			throw new AppointmentNotFoundException("Appointment does not exist!!");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	//Cancel Appointment
	@PutMapping("/cancelAppointment/{doctorid},{appointmentid}")
	public ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable int doctorid,@PathVariable int appointmentid) throws AppointmentNotFoundException{
		
		AppointmentDTO updated=service.cancelAppointment(doctorid,appointmentid);
		if(updated==null) {
			throw new AppointmentNotFoundException("Appointment does not exist!!");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	//Reschedule Appointment
	@PutMapping("/rescheduleAppointment/{doctorid}/{appointmentid}/{date}/{time}")
	public ResponseEntity<AppointmentDTO> rescheduleAppointment(@PathVariable int doctorid,@PathVariable int appointmentid,@PathVariable LocalDate date,@PathVariable String time) throws AppointmentNotFoundException{
		
		LocalTime appointtime=LocalTime.parse(time);
		AppointmentDTO updated=service.rescheduleAppointment(doctorid,appointmentid,date,appointtime);
		if(updated==null) {
			throw new AppointmentNotFoundException("Appointment does not exist!!");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	//Consultation
	@PostMapping("/conductAppointment/{appointmentid}")
	public ResponseEntity<MedicalRecordDTO> conductAppointment(@PathVariable int appointmentid,@RequestBody MedicalRecordDTO record) throws AppointmentNotFoundException{
		
		MedicalRecordDTO updated=service.conductAppointment(appointmentid,record);
		if(updated==null) {
			throw new AppointmentNotFoundException("Appointment does not exist!!");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	@PutMapping("/editRecord/{recordid}")
	public ResponseEntity<MedicalRecordDTO> editRecord(@PathVariable int recordid,@RequestBody MedicalRecordDTO record) throws RecordsNotFoundException{
		
		MedicalRecordDTO updated=service.editRecord(recordid,record);
		if(updated==null) {
			throw new RecordsNotFoundException("Medical record with ID " + recordid + " not found");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	 @PostMapping("/add/{recordId}")
	    public ResponseEntity<PrescriptionDTO> addPrescription(@PathVariable int recordId,@RequestBody PrescriptionDTO prescriptionDTO) throws RecordsNotFoundException {

	            PrescriptionDTO addedPrescription = service.addPrescription(recordId, prescriptionDTO);
	            if(addedPrescription==null) {
	            	throw new RecordsNotFoundException("Medical record not found for id: " + recordId);
	            }
	            return new ResponseEntity<>(addedPrescription,HttpStatus.CREATED);
	        
	    }
	
	@PutMapping("/editRecord/{recordid}/{prescriptionid}")
	public ResponseEntity<PrescriptionDTO> editPrescriptions(@PathVariable int recordid,@PathVariable int prescriptionid,@RequestBody PrescriptionDTO Prescription) throws PrescriptionsNotFoundException{
		
		PrescriptionDTO updated=service.editPrescriptions(recordid,prescriptionid,Prescription);
		if(updated==null) {
			throw new PrescriptionsNotFoundException("Medical record with ID " + recordid + " not found");
		}
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	 @DeleteMapping("/delete/{recordId}/{prescriptionId}")
	    public ResponseEntity<String> deletePrescription(@PathVariable int recordId,@PathVariable int prescriptionId) {
	        
	        boolean isDeleted = service.deletePrescription(recordId, prescriptionId);

	        if (isDeleted) {
	            return ResponseEntity.ok("Prescription deleted successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Prescription not found or does not belong to the specified medical record.");
	        }
	    }
	
}
