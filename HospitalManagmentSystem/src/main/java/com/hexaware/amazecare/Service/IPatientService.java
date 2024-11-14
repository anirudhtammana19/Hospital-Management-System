package com.hexaware.amazecare.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PatientDetailsDTO;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.PatientNotFoundException;

public interface IPatientService {


	public PatientDetailsDTO savedata(PatientDTO pd);

	public PatientDetailsDTO updateprofile(int id, PatientDTO pd) throws PatientNotFoundException;

	public List<AppointmentDetailsDTO> getpatientappoints(int patientid);

	public List<MedicalRecordDetailsDTO> getpatientmedicalrecords(int patientid);
	
	public AppointmentDetailsDTO bookanappointment(AppointmentDTO a, int patientid, int doctorid)
			throws DoctorNotFoundException, PatientNotFoundException ;

	public AppointmentDetailsDTO rescheduleAppointmentByPatient(int appointmentid, LocalDate date, LocalTime time);
	
	public List<DoctorDetailsDTO> getAvailableDoctors(String speciality);
	
	public PatientDetailsDTO viewPatientProfile(int patientid);
}
