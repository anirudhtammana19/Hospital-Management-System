package com.hexaware.amazecare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Appointment;

interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

}


