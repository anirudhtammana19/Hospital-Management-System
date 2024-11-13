package com.hexaware.amazecare.Service;
import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Appointment.Status;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AppointmentRepo appointRepo;
	
	@Autowired
	MedicalRecordRepo medicalRepo;
	
	@Autowired
	ModelMapper model;
	
	public DoctorDTO editDoctorProfile(int doctorid, DoctorDTO doc) {
		
		
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		Users u= userRepo.findByUsername(doctor.getEmail());
		if(u==null) {
			return null;
		}
		u.setUsername(doc.getEmail());
		u.setPassword(doc.getPassword());
		userRepo.save(u);
		doctor=model.map(doc, Doctor.class);
		doctor.setDoctorId(doctorid);
		doctor.setUser(u);
		Doctor updatedDoctor=doctorRepo.save(doctor);
		return model.map(updatedDoctor, DoctorDTO.class);
	}

	public DoctorDTO viewDoctorProfile(int doctorid) {
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		
		return model.map(doctor, DoctorDTO.class);
	}

	public List<AppointmentDTO> viewDoctorAppointments(int doctorid) {
		
		List<Appointment> appointments=appointRepo.findByDoctor_DoctorId(doctorid);
		
		 return appointments.stream().map(i -> {
		        AppointmentDTO dto = model.map(i, AppointmentDTO.class);
		        dto.setDoctorFirstName(i.getDoctor().getFirstName());
		        dto.setPatientFirstName(i.getPatient().getFirstName());
		        return dto;
		    }).toList();
	}

	public List<MedicalRecordDTO> viewPatientMedicalRecords(int patientid) {
		List<MedicalRecord> records= medicalRepo.findByPatient_PatientId(patientid);
		
		 return records.stream().map(i -> {
		        MedicalRecordDTO dto = model.map(i, MedicalRecordDTO.class);
		        dto.setDoctorFirstName(i.getDoctor().getFirstName());
		        dto.setPatientFirstName(i.getPatient().getFirstName());
		        return dto;
		    }).toList();
	}

	public AppointmentDTO acceptAppointment(int doctorid, int appointmentid) {
		
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid && app.getStatus().equals(Status.REQUESTED)) {
				app.setStatus(Status.SCHEDULED);
				appointRepo.save(app);
				AppointmentDTO appDTO=model.map(app, AppointmentDTO.class);
				appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
				appDTO.setPatientFirstName(app.getPatient().getFirstName());
				return appDTO;
			}
		}
		return null;
	}

	public AppointmentDTO cancelAppointment(int doctorid, int appointmentid) {
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid ) {
				app.setStatus(Status.CANCELLED);
				appointRepo.save(app);
				AppointmentDTO appDTO=model.map(app, AppointmentDTO.class);
				appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
				appDTO.setPatientFirstName(app.getPatient().getFirstName());
				return appDTO;
			}
		}
		return null;
	}

	public AppointmentDTO rescheduleAppointment(int doctorid, int appointmentid, LocalDate date, LocalTime time) {
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid && app.getStatus().equals(Status.SCHEDULED)) {
				app.setAppointmentDate(date);
				app.setAppointmentTime(time);
				app.setStatus(Status.RESCHEDULED);
				appointRepo.save(app);
				AppointmentDTO appDTO=model.map(app, AppointmentDTO.class);
				appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
				appDTO.setPatientFirstName(app.getPatient().getFirstName());
				return appDTO;
			}
		}
		return null;
	}
	

	
	
	
	
	
}
