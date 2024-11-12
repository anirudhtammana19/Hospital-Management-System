package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare.Model.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {

	public List<Doctor> findBySpecialtyStartingWith(String speciality);
	
	public List<Doctor> findByFirstNameStartingWith(String firstName);
}
