package com.hexaware.amazecare.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.Patient;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {

	public Optional<Doctor> findByEmail(String email);
	
	public List<Doctor> findBySpecialtyStartingWith(String speciality);
	
	public List<Doctor> findByFirstNameStartingWith(String firstName);

	@Query("SELECT DISTINCT d.specialty FROM Doctor d")
    List<String> findAllDistinctSpecialties();

}
