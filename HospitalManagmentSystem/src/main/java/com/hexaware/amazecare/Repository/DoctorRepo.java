package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Doctor;

interface DoctorRepo extends JpaRepository<Doctor,Integer> {

	public List<Doctor> findBySpecialtyStartingWith(String speciality);
	
	public List<Doctor> findByFirstNameStartingWith(String firstName);
}
