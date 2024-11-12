package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.MedicalRecord;

public interface MedicalRecordRepo extends JpaRepository<MedicalRecord,Integer> {

	List<MedicalRecord> findByDoctor_DoctorId(int doctorID);
	
	List<MedicalRecord> findByPatient_PatientId(int patientID);
	
}


