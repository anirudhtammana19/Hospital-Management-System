package com.hexaware.amazecare.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Patient;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
	
	public Optional<Patient> findByEmail(String email);
	
	public List<Patient> findByFirstNameStartingWith(String firstName);
}
