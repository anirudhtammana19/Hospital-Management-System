package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Users;

interface PrescriptionRepo extends JpaRepository<Users,Integer> {

}


