package com.hexaware.amazecare.Service;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper model;
	
	public DoctorDTO editDoctorProfile(int doctorid, DoctorDTO doc) {
		
		Users u= userRepo.findByUsername(doc.getEmail());
		u.setPassword(doc.getPassword());
		userRepo.save(u);
		Doctor doctor=model.map(doc, Doctor.class);
		Doctor updatedDoctor=doctorRepo.save(doctor);
		return model.map(updatedDoctor, DoctorDTO.class);
	}
	
	
	
	
	
}
