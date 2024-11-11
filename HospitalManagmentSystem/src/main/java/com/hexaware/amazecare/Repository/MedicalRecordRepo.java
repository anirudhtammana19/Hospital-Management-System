package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.MedicalRecord;

interface MedicalRecordRepo extends JpaRepository<MedicalRecord,Integer> {

}


