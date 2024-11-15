package com.hexaware.amazecare.Service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PatientDetailsDTO;
import com.hexaware.amazecare.DTO.UsersDTO;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Patient;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Model.Users.Role;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.PatientRepo;
import com.hexaware.amazecare.Repository.UserRepo;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	DoctorRepo doctorRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserRepo userRepo;
	@Autowired
	AppointmentRepo appointRepo;
	@Autowired
	PatientRepo patientRepo;
	@Autowired
	MedicalRecordRepo medicalRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//Part of Life cycle of Spring Core
    @PostConstruct
    public void init() {
        if (userRepo.findByRole(Role.ADMIN)==null) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // hashed password
            admin.setRole(Role.ADMIN);
            userRepo.save(admin);
        }
    }

	
	public DoctorDetailsDTO addadoctor(DoctorDTO d) {
		
		Doctor doctor = modelMapper.map(d, Doctor.class);
		
		Users user = new Users();
		user.setUsername(d.getEmail());
		user.setPassword(passwordEncoder.encode(d.getPassword())); 
		user.setRole(Users.Role.DOCTOR);
		
		userRepo.save(user);
		doctor.setUser(user);
		
		doctorRepo.save(doctor);
		return modelMapper.map(doctor, DoctorDetailsDTO.class);
		
		
	} 
	
	 public List<String> getDistinctSpecialties() {
	        return doctorRepo.findAllDistinctSpecialties();
	    }
	 
	public List<DoctorDetailsDTO> viewAllDoctors() {
		List<Doctor> list = doctorRepo.findAll();
		if(list.isEmpty()) {
			return null;
		}
		List<DoctorDetailsDTO> out=list.stream().map(i->{
			DoctorDetailsDTO j=modelMapper.map(i,DoctorDetailsDTO.class);
			return j;}).toList();
		return out;
	}

	public List<PatientDetailsDTO> viewAllPatients() {
		List<Patient> list = patientRepo.findAll();
		if(list.isEmpty()) {
			return null;
		}
		List<PatientDetailsDTO> out=list.stream().map(i->{
			PatientDetailsDTO j=modelMapper.map(i,PatientDetailsDTO.class);
			return j;}).toList();
		return out;
		
		
	}

	public List<MedicalRecordDetailsDTO> viewAllRecords() {
		List<MedicalRecord> list = medicalRepo.findAll();
		if(list.isEmpty()) {
			return null;
		}
		return list.stream().map(i->modelMapper.map(i,MedicalRecordDetailsDTO.class)).toList();
	}

	public List<AppointmentDetailsDTO> viewAllAppointments() {
		List<Appointment> list = appointRepo.findAll();
		if(list.isEmpty()) {
			return null;
		}
		return list.stream().map(i->modelMapper.map(i,AppointmentDetailsDTO.class)).toList();
		
	}

	
	public String deleteaappointment(int appointmentid) {
		
		Appointment appointment = appointRepo.findById(appointmentid).orElse(null);
		if (appointment!=null) {
			
			appointRepo.delete(appointment);
			return "Appointment deleted successfully";
		}
		return "Appointment not found";
	}
	
	public String deletedoctorbyid(int doctorid)  {
		Doctor doctor = doctorRepo.findById(doctorid).orElse(null);
		if(doctor==null) {
			return null;
			
		}
		doctorRepo.delete(doctor);
		return "Doctor deleted successfully";
	}
	
	public String deletepatientbyid(int patientid)  {
		Patient patient = patientRepo.findById(patientid).orElse(null);
		if(patient==null) {
			return null;
			
		}
		patientRepo.delete(patient);
		return "Doctor deleted successfully";
	}
	
	public String deleterecordbyid(int recordid)  {
		MedicalRecord record = medicalRepo.findById(recordid).orElse(null);
		if(record==null) {
			return null;
			
		}
		medicalRepo.delete(record);
		return "Doctor deleted successfully";
	}

	public UsersDTO editAdmin(UsersDTO d) {
		Users admin=userRepo.findByRole(Role.ADMIN);
		if(d.getUsername()!=null) {
			admin.setUsername(d.getUsername());
		}
		if(d.getPassword()!=null) {
			admin.setPassword(passwordEncoder.encode(d.getPassword()));
		}
		Users updated=userRepo.save(admin);
		return modelMapper.map(updated, UsersDTO.class);
	}


	public List<DoctorDetailsDTO> viewDoctorsByName(String name) {
		List<Doctor> doc=doctorRepo.findByFirstNameStartingWith(name);
		if(doc.isEmpty()) {
			return null;
		}	
		return doc.stream().map(i->modelMapper.map(i, DoctorDetailsDTO.class)).toList() ;
	}


	public List<PatientDetailsDTO> viewPatientsByName(String name) {
		List<Patient> patients=patientRepo.findByFirstNameStartingWith(name);
		if(patients.isEmpty()) {
			return null;
		}	
		return patients.stream().map(i->modelMapper.map(i, PatientDetailsDTO.class)).toList() ;
	}

}
