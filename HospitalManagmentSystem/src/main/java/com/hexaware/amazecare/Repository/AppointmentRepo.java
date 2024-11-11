package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Appointment;

interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

	 List<Appointment> findByPatient_PatientId(int patientId);
	 
	 List<Appointment> findByDoctor_DoctorId(int doctorId);
	 
}


