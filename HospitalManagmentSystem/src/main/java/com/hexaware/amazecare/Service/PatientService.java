package com.hexaware.amazecare.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Exception.PatientNotFoundException;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Appointment.Status;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Patient;
import com.hexaware.amazecare.Model.Patient.BloodGroup;
import com.hexaware.amazecare.Model.Patient.Gender;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.PatientRepo;
import com.hexaware.amazecare.Repository.UserRepo;

@Service
public class PatientService {

	@Autowired
	PatientRepo pr;
	@Autowired
	UserRepo ur;
	@Autowired
	ModelMapper mapper;
	@Autowired
	MedicalRecordRepo mr;
	@Autowired
	AppointmentRepo ar;
	@Autowired
	DoctorRepo dr;

	public PatientDTO savedata(PatientDTO pd) {

		Patient patient = mapper.map(pd, Patient.class);
		Users user = new Users();
		user.setUsername(pd.getEmail());
		user.setPassword(pd.getPassword());
		user.setRole(Users.Role.PATIENT);
		Users savedUser = ur.save(user);

		patient.setUser(savedUser);

		pr.save(patient);

		return mapper.map(patient, PatientDTO.class);
	}

	public PatientDTO updateprofile(int id, PatientDTO pd) throws PatientNotFoundException  {
	   
		Patient existingPatient = pr.findById(id).orElse(null);
		if(existingPatient==null) {
			return null;
		}
	    if(pd.getFirstName()!=null) {
	    	existingPatient.setFirstName(pd.getFirstName());
	    }
	    if(pd.getLastName()!=null) {
	    	existingPatient.setLastName(pd.getLastName());
	    }
	    if(pd.getDateOfBirth()!=null) {
	    	existingPatient.setDateOfBirth(pd.getDateOfBirth());
	    }
	    if(pd.getGender()!=null) {
	    	existingPatient.setGender(Gender.valueOf(pd.getGender()));
	    }
	    if(pd.getContactNumber()!=null) {
	    	existingPatient.setContactNumber(pd.getContactNumber());
	    }
	    if(pd.getEmail()!=null) {
	    	existingPatient.setEmail(pd.getEmail());
	    }
	    if(pd.getAddress()!=null) {
	    	existingPatient.setAddress(pd.getAddress());
	    }
	    if(pd.getEmergencyContact()!=null) {
	    	existingPatient.setEmergencyContact(pd.getEmergencyContact());
	    }
	    if(pd.getAllergies()!=null) {
	    	existingPatient.setAllergies(pd.getAllergies());
	    }
	    if(pd.getAadharCard()!=null) {
	    	existingPatient.setAadharCard(pd.getAadharCard());
	    }
	     
	    if(pd.getBloodGroup()!=null) {
	    	existingPatient.setBloodGroup(BloodGroup.valueOf(pd.getBloodGroup()));
	    }
	    
	    Users u = ur.findByUsername(pd.getEmail()); 
	    if (u != null) {
	    	if(pd.getEmail()!=null) {
	    		u.setUsername(pd.getEmail());
		    }
	    	if(pd.getPassword()!=null) {
	    		u.setPassword(pd.getPassword());
		    }
	    	existingPatient.setUser(u);
	        ur.save(u); 
	    }

	    pr.save(existingPatient);

	    return mapper.map(existingPatient, PatientDTO.class);
	}

	public List<AppointmentDTO> getpatientappoints(int patientid) {

		List<Appointment> appointment = ar.findByPatient_PatientId(patientid);

		List<AppointmentDTO> appointmentDTOs = appointment.stream().map(i -> mapper.map(i, AppointmentDTO.class))
				.collect(Collectors.toList());

		return appointmentDTOs;

	}

	public List<MedicalRecordDTO> getpatientmedicalrecords(int patientid) {

		List<MedicalRecord> record = mr.findByPatient_PatientId(patientid);

		List<MedicalRecordDTO> recorddto = record.stream().map(i -> mapper.map(i, MedicalRecordDTO.class))
				.collect(Collectors.toList());

		return recorddto;

	}

	/*
	 * public String deleteaappointment(int appointmentid) {
	 * 
	 * Appointment appointment = ar.findById(appointmentid).orElse(null); if
	 * (appointment!=null) {
	 * 
	 * appointment.setStatus(Appointment.Status.CANCELLED); ar.save(appointment);
	 * return "Appointment cancelled successfully"; } return
	 * "Appointment not found"; }
	 */

	public AppointmentDTO bookanappointment(AppointmentDTO a, int patientid, int doctorid)
			throws DoctorNotFoundException, PatientNotFoundException {

		Appointment appointment = mapper.map(a, Appointment.class);

		Optional<Doctor> doctorOpt = dr.findById(doctorid);
		Optional<Patient> patientOpt = pr.findById(patientid);

		if (doctorOpt.isPresent() && patientOpt.isPresent()) {

			Doctor doctor = doctorOpt.get();
			Patient patient = patientOpt.get();

			appointment.setDoctor(doctor);
			appointment.setPatient(patient);
			appointment.setStatus(Appointment.Status.REQUESTED); // default status

			ar.save(appointment);

			return mapper.map(appointment, AppointmentDTO.class);
		} else {
			if (!doctorOpt.isPresent()) {
				throw new DoctorNotFoundException("Doctor with ID " + doctorid + " not found");
			}
			throw new PatientNotFoundException("Patient with ID " + patientid + " not found");
		}
	}

	public AppointmentDTO rescheduleAppointmentByPatient(int appointmentid, LocalDate date, LocalTime time) {
		Appointment app = ar.findById(appointmentid).orElse(null);
		if (app != null && (app.getStatus().equals(Status.SCHEDULED) || app.getStatus().equals(Status.RESCHEDULED))) {
			app.setAppointmentDate(date);
			app.setAppointmentTime(time);
			app.setStatus(Status.RESCHEDULED);
			ar.save(app);
			AppointmentDTO appDTO = mapper.map(app, AppointmentDTO.class);
			return appDTO;
		} else {
			return null;
		}

	}

	public List<DoctorDTO> getAvailableDoctors(String speciality) {
		List<Doctor> doc = dr.findBySpecialtyStartingWith(speciality);
		if(doc.isEmpty()) {
			return null;
		}
		return doc.stream().map(i ->{
			DoctorDTO dto=mapper.map(i, DoctorDTO.class);
					dto.setPassword("Forbidden");
			return dto;
		}).toList();
	}

	public PatientDTO viewPatientProfile(int patientid) {
		Patient doctor=pr.findById(patientid).orElse(null);
		
		return mapper.map(doctor, PatientDTO.class);
	}

}
