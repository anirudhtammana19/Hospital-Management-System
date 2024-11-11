package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Users;

interface AppointmentRepo extends JpaRepository<Users,Integer> {

}


