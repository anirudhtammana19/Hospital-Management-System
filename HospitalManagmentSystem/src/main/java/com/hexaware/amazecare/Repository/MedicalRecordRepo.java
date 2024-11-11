package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Users;

interface MedicalRecordRepo extends JpaRepository<Users,Integer> {

}


