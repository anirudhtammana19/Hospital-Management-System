package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Patient;

interface PatientRepo extends JpaRepository<Patient,String> {

}
