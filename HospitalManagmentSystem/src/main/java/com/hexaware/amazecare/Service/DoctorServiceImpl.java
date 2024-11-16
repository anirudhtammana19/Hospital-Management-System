package com.hexaware.amazecare.Service;
import com.hexaware.amazecare.DTO.AppointmentDetailsDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDetailsDTO;
import com.hexaware.amazecare.DTO.PrescriptionDTO;
import com.hexaware.amazecare.DTO.PrescriptionDetailsDTO;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Appointment.Status;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Prescription;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.PrescriptionRepo;
import com.hexaware.amazecare.Repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorServiceImpl implements IDoctorService{

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AppointmentRepo appointRepo;
	
	@Autowired
	MedicalRecordRepo medicalRepo;
	
	@Autowired
	PrescriptionRepo prescriptionRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	Logger logger= LoggerFactory.getLogger(DoctorServiceImpl.class);
	
	public DoctorDetailsDTO editDoctorProfile(int doctorid, DoctorDTO doc) {
		logger.info("Request initiated to edit Doctor Profile!");
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		
		if(doctor==null) {
			logger.warn("No Doctor found with id: "+doctorid);
			return null;
		}
		Users u = userRepo.findByUsername(doctor.getEmail()).get(); 
	    if(doc.getFirstName()!=null) {
	    	doctor.setFirstName(doc.getFirstName());
	    }
	    if(doc.getLastName()!=null) {
	    	doctor.setLastName(doc.getLastName());
	    }
	    if(doc.getGender()!=null) {
	    	doctor.setGender(doc.getGender());
	    }
	    if(doc.getContactNumber()!=null) {
	    	doctor.setContactNumber(doc.getContactNumber());
	    }
	    if(doc.getEmail()!=null) {
	    	if(userRepo.findByUsername(doc.getEmail()).isPresent()) {
	    		logger.info("An User exists with given Email id!");
	    	}else {
		    	doctor.setEmail(doc.getEmail());
		    	u.setUsername(doc.getEmail());	
	    	}
	    }
	    if(doc.getProfile_image()!=null) {
	    	doctor.setProfile_image(doc.getProfile_image());
	    }
	    if(doc.getDesignation()!=null) {
	    	doctor.setDesignation(doc.getDesignation());
	    }
	    if(doc.getExperience()!=0) {
	    	doctor.setExperience(doc.getExperience());
	    }
	    if(doc.getQualification()!=null) {
	    	doctor.setQualification(doc.getQualification());
	    }
	     
	    if(doc.getBloodGroup()!=null) {
	    	doctor.setBloodGroup(doc.getBloodGroup());
	    }
	    
	    if(doc.getSpecialty()!=null) {
	    	doctor.setSpecialty(doc.getSpecialty());
	    }
	    
	    if (u != null) {
	    	
	    	if(doc.getPassword()!=null) {
	    		u.setPassword(passwordEncoder.encode(doc.getPassword()));
		    }
	    	doctor.setUser(u);
	        userRepo.save(u); 
	    }
	    doctorRepo.save(doctor);
	    logger.info("Doctor details edited successfully!");
	    return modelMapper.map(doctor, DoctorDetailsDTO.class);
		}

	public DoctorDetailsDTO viewDoctorProfile() {
		logger.info("Request initiated to view Doctor Profile!");
		Optional<Doctor> doctor=getCurrentDoctor();
		if(doctor.isEmpty()) {
			logger.warn("Unable to view Doctor Profile!");
			return null;
		}
		return modelMapper.map(doctor.get(), DoctorDetailsDTO.class);
	}

	public List<AppointmentDetailsDTO> viewDoctorAppointments(int doctorid) {
		logger.info("Request initiated to view Appointments of doctor with id: "+doctorid);
		List<Appointment> appointments=appointRepo.findByDoctor_DoctorId(doctorid);
		
		 return appointments.stream().map(i -> modelMapper.map(i, AppointmentDetailsDTO.class)).toList();
	}

	public List<MedicalRecordDetailsDTO> viewPatientMedicalRecords(int patientid) {
		logger.info("Request initiated to view Medical records of patient with id: "+patientid);
		List<MedicalRecord> records= medicalRepo.findByPatient_PatientId(patientid);
		
		 return records.stream().map(i -> modelMapper.map(i, MedicalRecordDetailsDTO.class)).toList();
	}

	public AppointmentDetailsDTO acceptAppointment(int doctorid, int appointmentid) {
		logger.info("Request initiated to accept Appointment with id: "+appointmentid);
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid && app.getStatus().equals(Status.REQUESTED)) {
				app.setStatus(Status.SCHEDULED);
				appointRepo.save(app);
				AppointmentDetailsDTO appDTO=modelMapper.map(app, AppointmentDetailsDTO.class);
				logger.info("Appointment is scheduled with doctor - id: "+doctorid);
				return appDTO;
			}
		}
		return null;
	}

	public AppointmentDetailsDTO cancelAppointment(int doctorid, int appointmentid) {
		logger.info("Request initiated to cancel Appointment with doctor - id: "+doctorid);
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid ) {
				app.setStatus(Status.CANCELLED);
				appointRepo.save(app);
				return modelMapper.map(app, AppointmentDetailsDTO.class);
			}
		}
		return null;
	}

	public AppointmentDetailsDTO rescheduleAppointment(int doctorid, int appointmentid, LocalDate date, LocalTime time) {
		logger.info("Request initiated to reschedule Appointment with doctor - id: "+doctorid);
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid && (app.getStatus().equals(Status.SCHEDULED) || app.getStatus().equals(Status.RESCHEDULED))) {
				app.setAppointmentDate(date);
				app.setAppointmentTime(time);
				app.setStatus(Status.RESCHEDULED);
				appointRepo.save(app);
				return modelMapper.map(app, AppointmentDetailsDTO.class);
			}
		}
		return null;
	}

	public MedicalRecordDetailsDTO conductAppointment(int appointmentid, MedicalRecordDTO record) {
		logger.info("Request initiated to start consultation of Appointment by doctor ");
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app.getStatus().equals(Status.CANCELLED)) {
			logger.warn("Request declined :Appointment was cancelled ");
			return null;
		}
		app.setStatus(Status.COMPLETED);
		MedicalRecord Mrecord=modelMapper.map(record, MedicalRecord.class);
		Mrecord.setAppointment(app);
		Mrecord.setDoctor(app.getDoctor());
		Mrecord.setPatient(app.getPatient());
		MedicalRecord updated=medicalRepo.save(Mrecord);
		logger.info("Appointment Completed!");
		return modelMapper.map(updated, MedicalRecordDetailsDTO.class);
		

	}

	
	public MedicalRecordDetailsDTO editRecord(int recordid, MedicalRecordDTO record) {
	    // Retrieve existing MedicalRecord by ID
		logger.info("Request initiated to edit Medical Record with id: "+recordid);
	    MedicalRecord Mrecord = medicalRepo.findById(recordid).orElse(null);
	    
	    if (Mrecord == null) {
	    	return null;
	    }

	    Mrecord.setSymptoms(record.getSymptoms());
	    Mrecord.setPhysicalExamination(record.getPhysicalExamination());
	    Mrecord.setTreatmentPlan(record.getTreatmentPlan());
	    Mrecord.setTestsRecommended(record.getTestsRecommended());
	    Mrecord.setNotes(record.getNotes());

	    MedicalRecord updated = medicalRepo.save(Mrecord);
	    logger.info("Medical Record updated Successfully!");
	    return modelMapper.map(updated, MedicalRecordDetailsDTO.class);
	    
	}


	public PrescriptionDetailsDTO editPrescriptions(int recordid, int prescriptionid, PrescriptionDTO prescription) {
		logger.info("Request initiated to edit prescription with id: "+prescriptionid);
		Prescription p=prescriptionRepo.findById(prescriptionid).orElse(null);
		if(p.getMedicalRecord().getRecordId()==recordid) {
			if(prescription.getDosage()!=null) {
				p.setDosage(prescription.getDosage());	
			}
			if(prescription.getDuration()!=0) {
				p.setDuration(prescription.getDuration());	
			}
			if(prescription.getFrequency()!=null) {
				p.setFrequency(prescription.getFrequency());	
			}
			if(prescription.getMedicationName()!=null) {
				p.setMedicationName(prescription.getMedicationName());	
			}
			if(prescription.getNotes()!=null) {
				p.setNotes(prescription.getNotes());	
			}
			Prescription updated=prescriptionRepo.save(p);
			MedicalRecord record=medicalRepo.findById(recordid).orElse(null);
			if(record!=null) {
				List<Prescription> prescriptions = record.getPrescriptions();
				for (int i = 0; i < prescriptions.size(); i++) {
	                if (prescriptions.get(i).getPrescriptionId() == prescriptionid) {
	                    prescriptions.set(i, updated);  // Replace the old prescription with the updated one
	                    break;
	                }
	            }
	            medicalRepo.save(record); 
				
			}
			logger.info("Prescription edited successfully!");
			return modelMapper.map(updated, PrescriptionDetailsDTO.class);
		}
		return null;
	}
	
	public boolean deletePrescription(int recordId, int prescriptionId) {
		logger.info("Request initiated to delete prescriptions with id: "+prescriptionId);
	    Prescription prescription = prescriptionRepo.findById(prescriptionId).orElse(null);
	    if (prescription != null && prescription.getMedicalRecord().getRecordId() == recordId) {

	        MedicalRecord record = prescription.getMedicalRecord();
	        record.getPrescriptions().remove(prescription);
	        medicalRepo.save(record);
	        prescriptionRepo.delete(prescription);
	        logger.info("Prescription deleted successfully!");
	        return true;  
	    }

	    return false;  
	}

	 public PrescriptionDetailsDTO addPrescription(int recordId, PrescriptionDTO prescriptionDTO) {
		 	logger.info("Request initiated to add prescription to Medical record with id: "+recordId);
	        MedicalRecord medicalRecord = medicalRepo.findById(recordId).orElse(null);
	        
	        if (medicalRecord == null) {
	            return null;
	        }
	        Prescription prescription = modelMapper.map(prescriptionDTO, Prescription.class);
	        
	        prescription.setMedicalRecord(medicalRecord);

	        Prescription savedPrescription = prescriptionRepo.save(prescription);
	        logger.info("Prescription added Successfully!");
	        return modelMapper.map(savedPrescription, PrescriptionDetailsDTO.class);
	    }
	 
	 private Optional<Doctor> getCurrentDoctor(){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			return doctorRepo.findByEmail(username);
		}
	
}
