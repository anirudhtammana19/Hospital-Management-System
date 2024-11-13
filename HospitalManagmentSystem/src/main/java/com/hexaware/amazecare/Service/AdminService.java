package com.hexaware.amazecare.Service;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Patient;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.PatientRepo;
import com.hexaware.amazecare.Repository.UserRepo;

@Service
public class AdminService {
	@Autowired
	DoctorRepo dr;
	@Autowired
	ModelMapper mapper;
	@Autowired
	UserRepo ur;
	@Autowired
	AppointmentRepo ar;
	@Autowired
	PatientRepo pr;
	@Autowired
	MedicalRecordRepo mr;
	
	public DoctorDTO addadoctor(DoctorDTO d) {
		
		Doctor doctor = mapper.map(d, Doctor.class);
		
		Users user = new Users();
		user.setUsername(d.getEmail());
		user.setPassword(d.getPassword());
		user.setRole(Users.Role.DOCTOR);
		
		ur.save(user);
		doctor.setUser(user);
		
		dr.save(doctor);
		return mapper.map(doctor, DoctorDTO.class);
		
		
	} 
	public String deleteaappointment(int appointmentid) {
		
		Appointment appointment = ar.findById(appointmentid).orElse(null);
		if (appointment!=null) {
			
			ar.delete(appointment);
			return "Appointment deleted successfully";
		}
		return "Appointment not found";
	}
	
	public String deletedoctorbyid(int doctorid)  {
		Doctor doctor = dr.findById(doctorid).orElse(null);
		if(doctor==null) {
			return null;
			
		}
		dr.delete(doctor);
		return "Doctor deleted successfully";
	}
	
	public String deletepatientbyid(int patientid)  {
		Patient patient = pr.findById(patientid).orElse(null);
		if(patient==null) {
			return null;
			
		}
		pr.delete(patient);
		return "Doctor deleted successfully";
	}
	
	public String deleterecordbyid(int recordid)  {
		MedicalRecord record = mr.findById(recordid).orElse(null);
		if(record==null) {
			return null;
			
		}
		mr.delete(record);
		return "Doctor deleted successfully";
	}

}
