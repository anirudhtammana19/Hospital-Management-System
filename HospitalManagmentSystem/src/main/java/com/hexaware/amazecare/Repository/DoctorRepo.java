package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Doctor;

interface DoctorRepo extends JpaRepository<Doctor,Integer> {

}
