package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Patient;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
	
	public List<Patient> findByFirstNameStartingWith(String firstName);
}
