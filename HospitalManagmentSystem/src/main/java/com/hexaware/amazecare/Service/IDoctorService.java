package com.hexaware.amazecare.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PrescriptionDTO;
import com.hexaware.amazecare.DTO.PrescriptionDetailsDTO;

public interface IDoctorService {


	public DoctorDetailsDTO editDoctorProfile(int doctorid, DoctorDTO doc);

	public DoctorDetailsDTO viewDoctorProfile(int doctorid);

	public List<AppointmentDetailsDTO> viewDoctorAppointments(int doctorid);

	public List<MedicalRecordDetailsDTO> viewPatientMedicalRecords(int patientid);

	public AppointmentDetailsDTO acceptAppointment(int doctorid, int appointmentid);

	public AppointmentDetailsDTO cancelAppointment(int doctorid, int appointmentid);

	public AppointmentDetailsDTO rescheduleAppointment(int doctorid, int appointmentid, LocalDate date, LocalTime time);
	
	public MedicalRecordDetailsDTO conductAppointment(int appointmentid, MedicalRecordDTO record);
	
	public MedicalRecordDetailsDTO editRecord(int recordid, MedicalRecordDTO record);

	public PrescriptionDetailsDTO editPrescriptions(int recordid, int prescriptionid, PrescriptionDTO prescription);
	
	public boolean deletePrescription(int recordId, int prescriptionId);
	
	public PrescriptionDetailsDTO addPrescription(int recordId, PrescriptionDTO prescriptionDTO);
}
