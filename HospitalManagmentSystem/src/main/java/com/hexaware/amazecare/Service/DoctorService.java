package com.hexaware.amazecare.Service;
import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PrescriptionDTO;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Appointment.Status;
import com.hexaware.amazecare.Model.Patient.BloodGroup;
import com.hexaware.amazecare.Model.Patient.Gender;
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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	PrescriptionRepo prescriptionRepo;
	
	@Autowired
	ModelMapper model;
	
	public DoctorDTO editDoctorProfile(int doctorid, DoctorDTO doc) {
		
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		if(doctor==null) {
			return null;
		}
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
	    	doctor.setEmail(doc.getEmail());
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
	    
	    Users u = userRepo.findByUsername(doc.getEmail()); 
	    if (u != null) {
	    	if(doc.getEmail()!=null) {
	    		u.setUsername(doc.getEmail());
		    }
	    	if(doc.getPassword()!=null) {
	    		u.setPassword(doc.getPassword());
		    }
	    	doctor.setUser(u);
	        userRepo.save(u); 
	    }
	    doctorRepo.save(doctor);
	    return model.map(doctor, DoctorDTO.class);
		}

	public DoctorDTO viewDoctorProfile(int doctorid) {
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		
		return model.map(doctor, DoctorDTO.class);
	}

	public List<AppointmentDTO> viewDoctorAppointments(int doctorid) {
		
		List<Appointment> appointments=appointRepo.findByDoctor_DoctorId(doctorid);
		
		 return appointments.stream().map(i -> model.map(i, AppointmentDTO.class)).toList();
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
			if(app.getDoctor().getDoctorId()==doctorid && (app.getStatus().equals(Status.SCHEDULED) || app.getStatus().equals(Status.RESCHEDULED))) {
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

	public MedicalRecordDTO conductAppointment(int appointmentid, MedicalRecordDTO record) {
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app.getStatus().equals(Status.CANCELLED)) {
			return null;
		}
		app.setStatus(Status.COMPLETED);
		MedicalRecord Mrecord=model.map(record, MedicalRecord.class);
		Mrecord.setAppointment(app);
		Mrecord.setDoctor(app.getDoctor());
		Mrecord.setPatient(app.getPatient());
		  List<Prescription> prescriptions = Mrecord.getPrescriptions();
		    for (Prescription prescription : prescriptions) {
		        prescription.setMedicalRecord(Mrecord);  // Set the relationship
		    }
		MedicalRecord updated=medicalRepo.save(Mrecord);
		MedicalRecordDTO recordDTO=model.map(updated, MedicalRecordDTO.class);
		recordDTO.setDoctorFirstName(app.getDoctor().getFirstName());
		recordDTO.setPatientFirstName(app.getPatient().getFirstName());
		return recordDTO;

	}

	
	public MedicalRecordDTO editRecord(int recordid, MedicalRecordDTO record) {
	    // Retrieve existing MedicalRecord by ID
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

	    MedicalRecordDTO recordDTO = model.map(updated, MedicalRecordDTO.class);
	    return recordDTO;
	}


	public PrescriptionDTO editPrescriptions(int recordid, int prescriptionid, PrescriptionDTO prescription) {
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
			return model.map(updated, PrescriptionDTO.class);
		}
		return null;
	}
	
	public boolean deletePrescription(int recordId, int prescriptionId) {
	    
	    Prescription prescription = prescriptionRepo.findById(prescriptionId).orElse(null);
	    if (prescription != null && prescription.getMedicalRecord().getRecordId() == recordId) {

	        MedicalRecord record = prescription.getMedicalRecord();
	        record.getPrescriptions().remove(prescription);
	        medicalRepo.save(record);


	        prescriptionRepo.delete(prescription);
	        return true;  
	    }

	    return false;  
	}

	 public PrescriptionDTO addPrescription(int recordId, PrescriptionDTO prescriptionDTO) {
	        
	        MedicalRecord medicalRecord = medicalRepo.findById(recordId).orElse(null);
	        
	        if (medicalRecord == null) {
	            return null;
	        }
	        Prescription prescription = model.map(prescriptionDTO, Prescription.class);
	        
	        prescription.setMedicalRecord(medicalRecord);

	        Prescription savedPrescription = prescriptionRepo.save(prescription);

	        return model.map(savedPrescription, PrescriptionDTO.class);
	    }
	
}
