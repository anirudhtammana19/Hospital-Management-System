package com.hexaware.amazecare.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.Exception.PatientNotFoundException;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Appointment.Status;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Patient;
import com.hexaware.amazecare.Model.Patient.BloodGroup;
import com.hexaware.amazecare.Model.Patient.Gender;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.PatientRepo;
import com.hexaware.amazecare.Repository.UserRepo;


@Service
public class PatientService {
	
	@Autowired
	PatientRepo pr;
	@Autowired
	UserRepo ur;
	@Autowired
	ModelMapper mapper;
	@Autowired
	MedicalRecordRepo mr;
	@Autowired
	AppointmentRepo ar;
	@Autowired
	DoctorRepo dr;
	
	
	public PatientDTO savedata(PatientDTO pd) {

	    
		Patient patient = mapper.map(pd, Patient.class);

	    
	    Users user = new Users();
	    user.setUsername(pd.getEmail());

	    
	    
	    user.setPassword(pd.getPassword());  
	    user.setRole(Users.Role.PATIENT);

	   
	    Users savedUser = ur.save(user);
	    
	    
	    patient.setUser(savedUser);

	    
	    pr.save(patient);

	    
	    return mapper.map(patient, PatientDTO.class);
	}
	
	public PatientDTO updateprofile(int id, PatientDTO pd) throws PatientNotFoundException  {
	   
	   /* Optional<Patient> existingPatientOpt = pr.findById(id);
	    if (!existingPatientOpt.isPresent()) {
	        throw new PatientNotFoundException("Patient not found");
	    }*/

	   
	   
		Patient existingPatient = pr.getById(id);

	    
	    existingPatient.setFirstName(pd.getFirstName());
	    existingPatient.setLastName(pd.getLastName());
	    existingPatient.setDateOfBirth(pd.getDateOfBirth());
	    existingPatient.setGender(Gender.valueOf(pd.getGender()));
	    existingPatient.setContactNumber(pd.getContactNumber());
	    existingPatient.setEmail(pd.getEmail()); 
	    existingPatient.setAddress(pd.getAddress());
	    existingPatient.setEmergencyContact(pd.getEmergencyContact());
	    existingPatient.setAllergies(pd.getAllergies());
	    existingPatient.setAadharCard(pd.getAadharCard()); 
	    existingPatient.setBloodGroup(BloodGroup.valueOf(pd.getBloodGroup()));

	    
	    Users u = ur.findByUsername(pd.getEmail()); 
	    if (u != null) {
	        u.setPassword(pd.getPassword());
	        ur.save(u); 
	    }

	    
	    pr.save(existingPatient);

	    return mapper.map(existingPatient, PatientDTO.class);
	}
	
	public List<AppointmentDTO> getpatientappoints(int patientid) {
		
		List<Appointment> appointment = ar.findByPatient_PatientId(patientid);
		
		List<AppointmentDTO> appointmentDTOs = appointment.stream().map(i->mapper.map(appointment, AppointmentDTO.class)).collect(Collectors.toList());
		
		return appointmentDTOs;
		
	}
	
	public List<MedicalRecordDTO> getpatientmedicalrecords(int patientid){
		
		List<MedicalRecord> record = mr.findByPatient_PatientId(patientid);
		
		List<MedicalRecordDTO> recorddto = record.stream().map(i->mapper.map(record, MedicalRecordDTO.class)).collect(Collectors.toList());
		
		return recorddto;
		
	}

	public String deleteaappointment(int appointmentid) {
		
		Appointment appointment = ar.findById(appointmentid).orElse(null);
		if (appointment!=null) {
			
			appointment.setStatus(Appointment.Status.CANCELLED);
			ar.save(appointment);
			return "Appointment canclled successfully";
		}
		return "Appointment not found";
	}
	
	
	public AppointmentDTO bookanappointment(AppointmentDTO a) {
		Appointment appointment = mapper.map(a, Appointment.class);
		
		Doctor doctor = (Doctor) dr.findByfirstName(a.getDoctorFirstName());
		
		if(doctor!=null) {
			
			appointment.setDoctor(doctor);
			
			appointment.setStatus(Appointment.Status.REQUESTED);
			
			ar.save(appointment);
			
			return mapper.map(appointment, AppointmentDTO.class);
		}
		return null;
	}

	public AppointmentDTO rescheduleAppointmentByPatient(int patientid, int appointmentid, LocalDate date, LocalTime time) {
	    Appointment app = ar.findById(appointmentid).orElse(null);
	    if (app != null) {
	        if (app.getPatient().getPatientId() == patientid && app.getStatus().equals(Status.SCHEDULED)) {
	            app.setAppointmentDate(date);
	            app.setAppointmentTime(time);
	            app.setStatus(Status.RESCHEDULED);
	            ar.save(app);
	            AppointmentDTO appDTO = mapper.map(app, AppointmentDTO.class);
	            appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
	            appDTO.setPatientFirstName(app.getPatient().getFirstName());
	            return appDTO;
	        }
	    }
	    return null;
	}

	

		
		
	
		
}
	

	
	



	


