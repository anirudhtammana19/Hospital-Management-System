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
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PatientDetailsDTO;
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

	public PatientDetailsDTO savedata(PatientDTO pd) {

		Patient patient = mapper.map(pd, Patient.class);
		Users user = new Users();
		user.setUsername(pd.getEmail());
		user.setPassword(pd.getPassword());
		user.setRole(Users.Role.PATIENT);
		Users savedUser = ur.save(user);

		patient.setUser(savedUser);

		pr.save(patient);

		return mapper.map(patient, PatientDetailsDTO.class);
	}

	public PatientDetailsDTO updateprofile(int id, PatientDTO pd) throws PatientNotFoundException  {
	   
		Patient existingPatient = pr.findById(id).orElse(null);
		
		if(existingPatient==null) {
			return null;
		}
		Users u = ur.findByUsername(existingPatient.getEmail());
		
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
	    	u.setUsername(pd.getEmail());
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
	    
	     
	    if (u != null) {
	    	
	    	if(pd.getPassword()!=null) {
	    		u.setPassword(pd.getPassword());
		    }
	    	existingPatient.setUser(u);
	        ur.save(u); 
	    }

	    Patient updated=pr.save(existingPatient);

	    return mapper.map(updated, PatientDetailsDTO.class);
	}

	public List<AppointmentDetailsDTO> getpatientappoints(int patientid) {

		List<Appointment> appointment = ar.findByPatient_PatientId(patientid);

		List<AppointmentDetailsDTO> appointmentDTOs = appointment.stream().map(i -> mapper.map(i, AppointmentDetailsDTO.class))
				.collect(Collectors.toList());

		return appointmentDTOs;

	}

	public List<MedicalRecordDetailsDTO> getpatientmedicalrecords(int patientid) {

		List<MedicalRecord> record = mr.findByPatient_PatientId(patientid);

		List<MedicalRecordDetailsDTO> recorddto = record.stream().map(i -> mapper.map(i, MedicalRecordDetailsDTO.class))
				.collect(Collectors.toList());

		return recorddto;

	}


	public AppointmentDetailsDTO bookanappointment(AppointmentDTO a, int patientid, int doctorid)
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

			Appointment updated=ar.save(appointment);

			return mapper.map(updated, AppointmentDetailsDTO.class);
		} else {
			if (!doctorOpt.isPresent()) {
				throw new DoctorNotFoundException("Doctor with ID " + doctorid + " not found");
			}
			throw new PatientNotFoundException("Patient with ID " + patientid + " not found");
		}
	}

	public AppointmentDetailsDTO rescheduleAppointmentByPatient(int appointmentid, LocalDate date, LocalTime time) {
		Appointment app = ar.findById(appointmentid).orElse(null);
		if (app != null && (app.getStatus().equals(Status.SCHEDULED) || app.getStatus().equals(Status.RESCHEDULED))) {
			app.setAppointmentDate(date);
			app.setAppointmentTime(time);
			app.setStatus(Status.RESCHEDULED);
			Appointment updated =ar.save(app);
			AppointmentDetailsDTO appDTO = mapper.map(updated, AppointmentDetailsDTO.class);
			return appDTO;
		} else {
			return null;
		}

	}

	public List<DoctorDetailsDTO> getAvailableDoctors(String speciality) {
		List<Doctor> doc = dr.findBySpecialtyStartingWith(speciality);
		if(doc.isEmpty()) {
			return null;
		}
		return doc.stream().map(i ->{
			DoctorDetailsDTO dto=mapper.map(i, DoctorDetailsDTO.class);
					dto.setPassword("Forbidden");
			return dto;
		}).toList();
	}

	public PatientDetailsDTO viewPatientProfile(int patientid) {
		Patient doctor=pr.findById(patientid).orElse(null);
		
		return mapper.map(doctor, PatientDetailsDTO.class);
	}

}
