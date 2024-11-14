package com.hexaware.amazecare.Service;

import java.util.List;

import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PatientDetailsDTO;
import com.hexaware.amazecare.DTO.UsersDTO;

public interface IAdminService {

	public DoctorDetailsDTO addadoctor(DoctorDTO d); 
	
	public List<String> getDistinctSpecialties();
	 
	public List<DoctorDetailsDTO> viewAllDoctors();
	
	public List<PatientDetailsDTO> viewAllPatients();

	public List<MedicalRecordDetailsDTO> viewAllRecords();
	
	public List<AppointmentDetailsDTO> viewAllAppointments();
	
	public String deleteaappointment(int appointmentid);
	
	public String deletedoctorbyid(int doctorid);
	
	public String deletepatientbyid(int patientid);
	
	public String deleterecordbyid(int recordid);
	
	public UsersDTO editAdmin(UsersDTO d);
	
	public List<DoctorDetailsDTO> viewDoctorsByName(String name);

	public List<PatientDetailsDTO> viewPatientsByName(String name);

}
