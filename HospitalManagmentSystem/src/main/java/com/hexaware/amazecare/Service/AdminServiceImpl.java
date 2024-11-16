package com.hexaware.amazecare.Service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger= LoggerFactory.getLogger(AdminServiceImpl.class);
	
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
		
		logger.info("Request initiated to register new Doctor");
		Optional<Doctor> doc=doctorRepo.findByEmail(d.getEmail());
		if(doc.isPresent()) {
			logger.warn("Doctor with Email:"+doc.get().getEmail()+" already Exists!!");
			return null;
		}
		Doctor doctor = modelMapper.map(d, Doctor.class);
		
		Users user = new Users();
		user.setUsername(d.getEmail());
		user.setPassword(passwordEncoder.encode(d.getPassword())); 
		user.setRole(Users.Role.DOCTOR);
		
		userRepo.save(user);
		doctor.setUser(user);
		
		Doctor updated=doctorRepo.save(doctor);
		logger.info("New Doctor registered Successfully!!");
		return modelMapper.map(updated, DoctorDetailsDTO.class);
		
		
	} 
	
	 public List<String> getDistinctSpecialties() {
		 logger.info("Request initiated to get distinct specialities of all available doctors!");
	        return doctorRepo.findAllDistinctSpecialties();
	    }
	 
	public List<DoctorDetailsDTO> viewAllDoctors() {
		logger.info("Request initiated to Get All Doctors!");
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
		logger.info("Request initiated to get all Patients!");
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
		logger.info("Request initiated to view all records!");
		List<MedicalRecord> list = medicalRepo.findAll();
		if(list.isEmpty()) {
			logger.warn("No Medical Records found!!");
			return null;
		}
		return list.stream().map(i->modelMapper.map(i,MedicalRecordDetailsDTO.class)).toList();
	}

	public List<AppointmentDetailsDTO> viewAllAppointments() {
		logger.info("Request initiated to view all appointments");
		List<Appointment> list = appointRepo.findAll();
		if(list.isEmpty()) {
			logger.warn("No Appointments found!");
			return null;
		}
		return list.stream().map(i->modelMapper.map(i,AppointmentDetailsDTO.class)).toList();
		
	}

	
	public String deleteaappointment(int appointmentid) {
		logger.info("Request initiated to delete an Appointment with id: "+appointmentid);
		Appointment appointment = appointRepo.findById(appointmentid).orElse(null);
		if (appointment!=null) {
			logger.info("Appointment deleted Successfully!");
			appointRepo.delete(appointment);
			return "Appointment deleted successfully";
		}
		logger.warn("No such appointment with id: "+appointmentid+"Exists!");
		return "Appointment not found";
	}
	
	public String deletedoctorbyid(int doctorid)  {
		logger.info("Request initiated to delete a doctor with id:"+doctorid);
		Doctor doctor = doctorRepo.findById(doctorid).orElse(null);
		if(doctor==null) {
			logger.warn("No such Doctor with id: "+doctorid+"Exists!");
			return null;
		}
		doctorRepo.delete(doctor);
		logger.info("Doctor deleted Successfully!");
		return "Doctor deleted successfully";
	}
	
	public String deletepatientbyid(int patientid)  {
		logger.info("Request initiated to delete a patient with id:"+patientid);
		Patient patient = patientRepo.findById(patientid).orElse(null);
		if(patient==null) {
			logger.warn("No such Patient with id: "+patientid+"Exists!");
			return null;
		}
		patientRepo.delete(patient);
		logger.info("Patient deleted Successfully!");
		return "Patient deleted successfully";
	}
	
	public String deleterecordbyid(int recordid)  {
		logger.info("Request initiated to delete a Medical Record with id:"+recordid);
		MedicalRecord record = medicalRepo.findById(recordid).orElse(null);
		if(record==null) {
			logger.warn("No such Medical Record with id: "+recordid+"Exists!");
			return null;
		}
		medicalRepo.delete(record);
		return "Medical Record deleted successfully";
	}

	public UsersDTO editAdmin(UsersDTO d) {
		logger.info("Request initiated to change Admin Username and Password");
		Users admin=userRepo.findByRole(Role.ADMIN);
		if(d.getUsername()!=null) {
			admin.setUsername(d.getUsername());
		}
		if(d.getPassword()!=null) {
			admin.setPassword(passwordEncoder.encode(d.getPassword()));
		}
		Users updated=userRepo.save(admin);
		logger.info("Admin Credentials Changed Successfully!");
		return modelMapper.map(updated, UsersDTO.class);
	}


	public List<DoctorDetailsDTO> viewDoctorsByName(String name) {
		logger.info("Request initiated to search doctors by name!");
		List<Doctor> doc=doctorRepo.findByFirstNameStartingWith(name);
		if(doc.isEmpty()) {
			logger.warn("No doctors exists with given name");
			return null;
		}	
		return doc.stream().map(i->modelMapper.map(i, DoctorDetailsDTO.class)).toList() ;
	}


	public List<PatientDetailsDTO> viewPatientsByName(String name) {
		logger.info("Request initiated to search patients by name!");
		List<Patient> patients=patientRepo.findByFirstNameStartingWith(name);
		if(patients.isEmpty()) {
			logger.warn("No patients exists with given name");
			return null;
		}	
		return patients.stream().map(i->modelMapper.map(i, PatientDetailsDTO.class)).toList() ;
	}

}
