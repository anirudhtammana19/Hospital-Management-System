package com.hexaware.amazecare.Service;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.Repository.DoctorRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo repo;

	public DoctorDTO editDoctorProfile(int doctorid, DoctorDTO doc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
