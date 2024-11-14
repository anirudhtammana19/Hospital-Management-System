package com.hexaware.amazecare.Service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
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
public class AdminService {
	@Autowired
	DoctorRepo doctorRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	UserRepo ur;
	@Autowired
	AppointmentRepo appointRepo;
	@Autowired
	PatientRepo patientRepo;
	@Autowired
	MedicalRecordRepo medicalRepo;
	
    @PostConstruct
    public void init() {
        if (ur.findByRole(Role.ADMIN)==null) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword("admin123"); // hashed password
            admin.setRole(Role.ADMIN);
            ur.save(admin);
        }
    }

	
	public DoctorDTO addadoctor(DoctorDTO d) {
		
		Doctor doctor = mapper.map(d, Doctor.class);
		
		Users user = new Users();
		user.setUsername(d.getEmail());
		user.setPassword(d.getPassword());
		user.setRole(Users.Role.DOCTOR);
		
		ur.save(user);
		doctor.setUser(user);
		
		doctorRepo.save(doctor);
		return mapper.map(doctor, DoctorDTO.class);
		
		
	} 
	
	public List<DoctorDTO> viewAllDoctors() {
		List<Doctor> list = doctorRepo.findAll();
		List<DoctorDTO> out=list.stream().map(i->{
			DoctorDTO j=mapper.map(i,DoctorDTO.class);
			j.setPassword(i.getUser().getPassword());
			return j;}).toList();
		return out;
	}

	public List<PatientDTO> viewAllPatients() {
		List<Patient> list = patientRepo.findAll();
		List<PatientDTO> out=list.stream().map(i->{
			PatientDTO j=mapper.map(i,PatientDTO.class);
			j.setPassword(i.getUser().getPassword());
			return j;}).toList();
		return out;
		
		
	}

	public List<MedicalRecordDTO> viewAllRecords() {
		List<MedicalRecord> list = medicalRepo.findAll();
		return list.stream().map(i->mapper.map(i,MedicalRecordDTO.class)).toList();
	}

	public List<AppointmentDTO> viewAllAppointments() {
		List<Appointment> list = appointRepo.findAll();
		return list.stream().map(i->mapper.map(i,AppointmentDTO.class)).toList();
		
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
		Users admin=ur.findByRole(Role.ADMIN);
		if(d.getUsername()!=null) {
			admin.setUsername(d.getUsername());
		}
		if(d.getPassword()!=null) {
			admin.setPassword(d.getPassword());
		}
		Users updated=ur.save(admin);
		return mapper.map(updated, UsersDTO.class);
	}


	public List<DoctorDTO> viewDoctorsByName(String name) {
		List<Doctor> doc=doctorRepo.findByFirstNameStartingWith(name);
		if(doc.isEmpty()) {
			return null;
		}	
		return doc.stream().map(i->mapper.map(i, DoctorDTO.class)).toList() ;
	}


	public List<PatientDTO> viewPatientsByName(String name) {
		List<Patient> patients=patientRepo.findByFirstNameStartingWith(name);
		if(patients.isEmpty()) {
			return null;
		}	
		return patients.stream().map(i->mapper.map(i, PatientDTO.class)).toList() ;
	}

}
