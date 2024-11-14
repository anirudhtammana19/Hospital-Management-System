package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

	//Custom Finder Method to get appointments by 
	 List<Appointment> findByPatient_PatientId(int patientId);
	 
	 List<Appointment> findByDoctor_DoctorId(int doctorId);
	 
	 
	 
}


