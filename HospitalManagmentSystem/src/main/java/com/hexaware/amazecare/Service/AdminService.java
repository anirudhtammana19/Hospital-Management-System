package com.hexaware.amazecare.Service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.UserRepo;

@Service
public class AdminService {
	@Autowired
	DoctorRepo dr;
	@Autowired
	ModelMapper mapper;
	@Autowired
	UserRepo ur;
	
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

}
