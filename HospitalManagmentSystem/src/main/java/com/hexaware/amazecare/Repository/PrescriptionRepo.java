package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Prescription;


public interface PrescriptionRepo extends JpaRepository<Prescription,Integer> {

}


