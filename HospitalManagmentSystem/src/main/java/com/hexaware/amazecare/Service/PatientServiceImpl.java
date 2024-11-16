package com.hexaware.amazecare.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
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
public class PatientServiceImpl implements IPatientService{

	@Autowired
	PatientRepo patientRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	MedicalRecordRepo medicalRecordRepo;
	@Autowired
	AppointmentRepo appointmentRepo;
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	Logger logger= LoggerFactory.getLogger(PatientServiceImpl.class);

	public PatientDetailsDTO savedata(PatientDTO pd) {
		logger.info("Request initiated to register new Patient");
		Optional<Patient> p=patientRepo.findByEmail(pd.getEmail());
		if(p.isPresent()) {
			return null;
		}
		Patient patient = modelMapper.map(pd, Patient.class);
		Users user = new Users();
		user.setUsername(pd.getEmail());
		user.setPassword(passwordEncoder.encode( pd.getPassword()));
		user.setRole(Users.Role.PATIENT);
		Users savedUser = userRepo.save(user);

		patient.setUser(savedUser);
		patientRepo.save(patient);
		logger.info("Patient registered successfully!");
		return modelMapper.map(patient, PatientDetailsDTO.class);
	}

	public PatientDetailsDTO updateprofile(int id, PatientDTO pd) throws PatientNotFoundException  {
		logger.info("Request initiated to edit Profile of patient with id: "+id);
		Patient existingPatient = patientRepo.findById(id).orElse(null);
		
		if(existingPatient==null) {
			return null;
		}
		Users u = userRepo.findByUsername(existingPatient.getEmail()).get();
		
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
	    	if(userRepo.findByUsername(pd.getEmail()).isPresent()) {
	    		logger.info("An User exists with given Email id!");
	    	}else {
		    	existingPatient.setEmail(pd.getEmail());
		    	u.setUsername(pd.getEmail());	
	    	}
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
	    		u.setPassword(passwordEncoder.encode( pd.getPassword()));
		    }
	    	existingPatient.setUser(u);
	        userRepo.save(u); 
	    }

	    Patient updated=patientRepo.save(existingPatient);
	    logger.info("Patient Profile was updated Successfully!");
	    return modelMapper.map(updated, PatientDetailsDTO.class);
	}

	public List<AppointmentDetailsDTO> getpatientappoints(int patientid) {
		logger.info("Request initiated to get Appointments of patient with id: "+patientid);
		List<Appointment> appointment = appointmentRepo.findByPatient_PatientId(patientid);

		List<AppointmentDetailsDTO> appointmentDTOs = appointment.stream().map(i -> modelMapper.map(i, AppointmentDetailsDTO.class))
				.collect(Collectors.toList());
		logger.info("Appointments of patient was retrieved Successfully!");
		return appointmentDTOs;

	}

	public List<MedicalRecordDetailsDTO> getpatientmedicalrecords(int patientid) {
		logger.info("Request initiated to get Medical Records of patient with id: "+patientid);
		List<MedicalRecord> record = medicalRecordRepo.findByPatient_PatientId(patientid);

		List<MedicalRecordDetailsDTO> recorddto = record.stream().map(i -> modelMapper.map(i, MedicalRecordDetailsDTO.class))
				.collect(Collectors.toList());
		logger.info("Medical Records of patient was retrieved Successfully!");
		return recorddto;

	}


	public AppointmentDetailsDTO bookanappointment(AppointmentDTO a, int patientid, int doctorid)
			throws DoctorNotFoundException, PatientNotFoundException {
		logger.info("Request initiated to book an appointment with doctor :"+doctorid+" by patient : "+patientid);
		Appointment appointment = modelMapper.map(a, Appointment.class);

		Optional<Doctor> doctorOpt = doctorRepo.findById(doctorid);
		Optional<Patient> patientOpt = patientRepo.findById(patientid);

		if (doctorOpt.isPresent() && patientOpt.isPresent()) {

			Doctor doctor = doctorOpt.get();
			Patient patient = patientOpt.get();

			appointment.setDoctor(doctor);
			appointment.setPatient(patient);
			appointment.setStatus(Appointment.Status.REQUESTED); // default status

			Appointment updated=appointmentRepo.save(appointment);
			logger.info("Appointment is requested!");
			return modelMapper.map(updated, AppointmentDetailsDTO.class);
		} else {
			if (!doctorOpt.isPresent()) {
				throw new DoctorNotFoundException("Doctor with ID " + doctorid + " not found");
			}
			throw new PatientNotFoundException("Patient with ID " + patientid + " not found");
		}
	}

	public AppointmentDetailsDTO rescheduleAppointmentByPatient(int appointmentid, LocalDate date, LocalTime time) {
		logger.info("Request initiated to reschedule an appointment!");
		Appointment app = appointmentRepo.findById(appointmentid).orElse(null);
		if (app.getPatient().getPatientId()==getCurrentPatient().get().getPatientId() && app != null && (app.getStatus().equals(Status.SCHEDULED) || app.getStatus().equals(Status.RESCHEDULED))) {
			app.setAppointmentDate(date);
			app.setAppointmentTime(time);
			app.setStatus(Status.RESCHEDULED);
			Appointment updated =appointmentRepo.save(app);
			AppointmentDetailsDTO appDTO = modelMapper.map(updated, AppointmentDetailsDTO.class);
			logger.info("Appointment was rescheduled Successfully!");
			return appDTO;
		} else {
			return null;
		}

	}

	public List<DoctorDetailsDTO> getAvailableDoctors(String speciality) {
		logger.info("Request initiated to get all available doctors with Speciality:"+speciality);
		List<Doctor> doc = doctorRepo.findBySpecialtyStartingWith(speciality);
		if(doc.isEmpty()) {
			logger.info("No doctors found with Speciality: "+speciality);
			return null;
		}
		return doc.stream().map(i ->{
			DoctorDetailsDTO dto=modelMapper.map(i, DoctorDetailsDTO.class);
			return dto;
		}).toList();
	}

	public PatientDetailsDTO viewPatientProfile() {
		logger.info("Request initiated to view Profile of patient");
		Optional<Patient> patient=getCurrentPatient();
		if(patient.isEmpty()) {
			return null;
		}
		return modelMapper.map(patient.get(), PatientDetailsDTO.class);
	}
	
	 private Optional<Patient> getCurrentPatient(){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			return patientRepo.findByEmail(username);
		}

}
